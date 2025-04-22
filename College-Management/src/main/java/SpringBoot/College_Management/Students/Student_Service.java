package SpringBoot.College_Management.Students;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
//import SpringBoot.College_Management.Security_Section.Owner_Details.Owner_Of_Entity;
import SpringBoot.College_Management.Professors.Professor_Entity;
import SpringBoot.College_Management.Security_Section.User_Entity;
import SpringBoot.College_Management.Students.Student_Details.Student;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
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


    // It Checks Id is present or not
    public void isExistByIdAndName(String studentId,String studentName) {
        boolean isExistById = studentRepository.findByStudentId(studentId);// checks the id is present or not
        if (!isExistById)
            throw new ResourceNotFound("Student Not Found with id : " + studentId);
        boolean isExistByName = studentRepository.existsByName(studentName);
        if (!isExistByName)
            throw new ResourceNotFound("Student Not Found with name : " + studentName);
    }

    public Optional<Student_DTO> getStudentByIdAndName(String studentId,String studentName) {
        return studentRepository.findByStudentIdAndName(studentId,studentName).map(studentsEntity -> modelMapper.map(studentsEntity, Student_DTO.class));
    }

    public List<Student_DTO> getAllStudents(String sortBy) {
        List<Student_Entity>  students = studentRepository.findBy(Sort.by(Sort.Direction.ASC,sortBy,"studentId","rollNo","dateOfAdmission"));
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
            ReflectionUtils.setField(fieldToUpdate, students, value);});

        Student_Entity updateRequiredField = studentRepository.save(students);
        return modelMapper.map(updateRequiredField,Student_DTO.class);

    }

    public Student_DTO getStudentByEmail(String email) {
        Student_Entity student =  studentRepository.findByEmail(email);
        return modelMapper.map(student,Student_DTO.class);
    }


    public String generateSecretCode(){
        Random random = new Random();
        int code = random.nextInt(10000000);
        return String.format("RU_STU"+"%d",code);
    }

//    public Student_Entity generateCustomId(Student_Entity student) {
//        long count = studentRepository.count() + 1;
//        String customId = "STU_" + String.format("%d", count);
//        student.setStudentId(customId);
//        return studentRepository.save(student);
//    }

//
//        public Student_DTO getStudentByEmail(String email) {
//        Student_Entity student =  studentRepository.findByEmail(email);
//        User_Entity user = (User_Entity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (student.getEmail().equals(user.getEmail())){
//            System.out.println("welcome");
//        }
//        else {
//            throw new ResourceNotFound("You are not the owner");
//        }
//        return modelMapper.map(student,Student_DTO.class);
//    }

}
