package SpringBoot.College_Management.Students;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.util.ReflectionUtils.findRequiredField;

@Service
@RequiredArgsConstructor
public class Student_Service {
    private final Student_Repository studentRepository;
    private final ModelMapper modelMapper;
//    private final Department_Repository departmentRepository;
//    private final Department_Entity departmentEntity;

    // It Checks Id is present or not
    public void isExistByIdAndName(Long studentId,String studentName) {
        boolean isExistById = studentRepository.existsById(studentId);// checks the id is present or not
        if (!isExistById)
            throw new ResourceNotFound("Student Not Found with id : " + studentId);
        boolean isExistByName = studentRepository.existsByName(studentName);
        if (!isExistByName)
            throw new ResourceNotFound("Student Not Found with name : " + studentName);
    }

    public Optional<Student_DTO> getStudentByName(Long studentId,String studentName) {
        return studentRepository.findByStudentIdAndName(studentId,studentName).map(studentsEntity -> modelMapper.map(studentsEntity, Student_DTO.class));
    }

    public List<Student_DTO> getAllStudents() {
        List<Student_Entity> students = studentRepository.findAll();
        return students
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, Student_DTO.class))
                .collect(Collectors.toList());
    }


    public Student_DTO addNewStudent(Student_DTO studentsDto) {
        Student_Entity students = modelMapper.map(studentsDto, Student_Entity.class);

        if (studentRepository.existsByRollNo(students.getRollNo())){
            throw new RuntimeException("Student already exist with Roll Number "+ students.getRollNo());
        }
        if (studentRepository.existsByEmail(students.getEmail())){
            throw new RuntimeException("Student already exist with email "+ students.getEmail());
        }

        Student_Entity savedStudent = studentRepository.save(students);

        return modelMapper.map(savedStudent, Student_DTO.class);
    }


    public Student_DTO updateStudent(Long studentId,String studentName, Student_DTO studentsDto) {
        isExistByIdAndName(studentId,studentName);
        Student_Entity students = modelMapper.map(studentsDto, Student_Entity.class);
        students.setStudentId(studentId);
        Student_Entity updatedStudent = studentRepository.save(students);
        return modelMapper.map(updatedStudent, Student_DTO.class);
    }

    @Transactional
    public boolean deleteStudentById(Long studentId,String studentName) {
        isExistByIdAndName(studentId,studentName) ;
        Student_Entity student = studentRepository.findByStudentIdAndName(studentId,studentName).get();
        studentRepository.deleteByStudentIdAndName(studentId,studentName);
        return true;
    }

    public Student_DTO partialUpdateStudentById(Long studentId,String studentName, Map<String, Object> updates) {
        isExistByIdAndName(studentId,studentName);
        Student_Entity students = studentRepository.findByStudentIdAndName(studentId,studentName).get();

        updates.forEach((field, value) -> {
            Field fieldToUpdate = findRequiredField(Student_Entity.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, students, value);});

        Student_Entity updateRequiredField = studentRepository.save(students);
        return modelMapper.map(updateRequiredField,Student_DTO.class);

    }
}
