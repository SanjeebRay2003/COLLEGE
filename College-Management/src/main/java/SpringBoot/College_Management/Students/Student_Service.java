package SpringBoot.College_Management.Students;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Security_Section.USER.User_Entity;
import SpringBoot.College_Management.Security_Section.USER.User_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static org.springframework.data.util.ReflectionUtils.findRequiredField;

@Service
@RequiredArgsConstructor
public class Student_Service {
    private final Student_Repository studentRepository;
    private final ModelMapper modelMapper;
    private final User_Repository userRepository;


    // It Checks Id is present or not
    public void isExistByIdAndName(String studentId,String studentName) {
        boolean isExistById = studentRepository.existsByStudentId(studentId);// checks the id is present or not
        if (!isExistById)
            throw new ResourceNotFound("Student Not Found with id : " + studentId);
        boolean isExistByName = studentRepository.existsByName(studentName);
        if (!isExistByName)
            throw new ResourceNotFound("Student Not Found with name : " + studentName);
    }

    public Optional<Student_DTO> getStudentByIdAndName(String studentId,String studentName) {
        return studentRepository.findByStudentIdAndName(studentId, studentName).map(studentsEntity -> modelMapper.map(studentsEntity, Student_DTO.class));
    }

    public List<Student_DTO> getAllStudents(String sortBy) {
        List<Student_Entity>  students = studentRepository.findBy(Sort.by(Sort.Direction.ASC,sortBy,"studentId","rollNo","dateOfAdmission","semester"));
        return students
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, Student_DTO.class))
                .collect(Collectors.toList());
    }


    public Student_DTO addNewStudent(Student_DTO studentsDto) {

        Student_Entity students = modelMapper.map(studentsDto, Student_Entity.class);

        long count = studentRepository.count() + 1;
        String customId = "STU" + String.format("%03d", count)+"RU";
        students.setStudentId(customId);
        String rollNumber = "RU"+studentsDto.getRollNo();
        students.setRollNo(rollNumber);

        if (studentRepository.existsByRollNo(students.getRollNo())){
            throw new RuntimeException("Student already exist with Roll Number "+ students.getRollNo());
        }

        if (studentRepository.existsByEmail(students.getEmail())){
            throw new RuntimeException("Student already exist with email "+ students.getEmail());
        }

        students.setSecretCode(generateSecretCode());

        if (studentRepository.existsBySecretCode(students.getSecretCode())){
            throw new RuntimeException("Student already exist with this secret code "+ students.getSecretCode());
        }

        Student_Entity savedStudent = studentRepository.save(students);

        return modelMapper.map(savedStudent, Student_DTO.class);
    }


    public Student_DTO updateStudent(String studentId,String studentName, Student_DTO studentsDto) {
        isExistByIdAndName(studentId,studentName);
        Student_Entity students = modelMapper.map(studentsDto, Student_Entity.class);
        students.setStudentId(studentId);
        students.setSecretCode(generateSecretCode());

        Optional<User_Entity> user = userRepository.findByEmail(students.getEmail());
        if (user.isPresent()){
            user.get().setStudentId(null);
            user.get().setSecretCode(null);
        }

        Optional<Student_Entity> studentEntity = studentRepository.findByStudentIdAndName(studentId,studentName);
        students.setCourse(studentEntity.get().getCourse());

        Optional<Student_Entity> existingEmail = studentRepository.findByEmail(studentsDto.getEmail());
        if (existingEmail.isPresent() && !existingEmail.get().getStudentId().equals(students.getStudentId())) {
            throw new RuntimeException("Email already exist for another Student " + studentsDto.getEmail()+ " -> "+existingEmail.get().getStudentId());
        }

        Optional<Student_Entity> existingRollNo = studentRepository.findByRollNo(studentsDto.getRollNo());
        if (existingRollNo.isPresent() && !existingRollNo.get().getStudentId().equals(students.getStudentId())) {
            throw new RuntimeException("Roll number already exist for another Student " + studentsDto.getRollNo()+ " -> "+existingRollNo.get().getStudentId());
        }

        if (!studentsDto.getDateOfAdmission().equals(existingEmail.get().getDateOfAdmission())){
            throw new IllegalArgumentException("Date Of Admission Cant Changeable");
        }

        Student_Entity updatedStudent = studentRepository.save(students);

        return modelMapper.map(updatedStudent, Student_DTO.class);
    }

    @Transactional
    public boolean deleteStudentById(String studentId,String studentName) {
        isExistByIdAndName(studentId,studentName) ;
        Student_Entity student = studentRepository.findByStudentIdAndName(studentId,studentName).get();
        studentRepository.deleteByStudentIdAndName(studentId,studentName);
        return true;
    }

    public Student_DTO partialUpdateStudentById(String studentId,String studentName, Map<String, Object> updates) {
        isExistByIdAndName(studentId,studentName);
        Student_Entity students = studentRepository.findByStudentIdAndName(studentId,studentName).get();

        updates.forEach((field, value) -> {
            Field fieldToUpdate = findRequiredField(Student_Entity.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, students, value);
        });

        Optional<Student_Entity> existingEmail = studentRepository.findByEmail(students.getEmail());
        if (existingEmail.isPresent() && !existingEmail.get().getStudentId().equals(students.getStudentId())) {
            throw new RuntimeException("Email already exist for another Student " + students.getEmail()+ " -> "+existingEmail.get().getStudentId());
        }

        Optional<Student_Entity> existingRollNo = studentRepository.findByRollNo(students.getRollNo());
        if (existingRollNo.isPresent() && !existingRollNo.get().getStudentId().equals(students.getStudentId())) {
            throw new RuntimeException("Roll number already exist for another Student " + students.getRollNo()+ " -> "+existingRollNo.get().getStudentId());
        }


        Student_Entity updateRequiredField = studentRepository.save(students);
        return modelMapper.map(updateRequiredField,Student_DTO.class);

    }


    public Student_DTO getStudentByEmail(String email) {
       Optional<Student_Entity> student =  studentRepository.findByEmail(email);
        return modelMapper.map(student,Student_DTO.class);
    }


    public String generateSecretCode(){
        Random random = new Random();
        int code = random.nextInt(10000000);
        return String.format("RU_STU"+"%d",code);
    }

    public Student_DTO getStudentByStudentId(String studentId) {
        Optional<Student_Entity> studentEntity = studentRepository.findByStudentId(studentId);
        return modelMapper.map(studentEntity,Student_DTO.class);
    }

    public Optional<Student_DTO> getAllDataByOwner(String studentId) {
        Optional<Student_Entity> studentEntity = studentRepository.findByStudentId(studentId);
        return Optional.ofNullable(modelMapper.map(studentEntity, Student_DTO.class));
    }



}
