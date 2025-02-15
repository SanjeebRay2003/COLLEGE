package SpringBoot.College_Management.Students;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

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
    public void isExistByID(Long studentId) {
        boolean isExist = studentRepository.existsById(studentId);// checks the id is present or not
        if (!isExist)
            throw new ResourceNotFound("Student Not Found with Id : " + studentId);
    }

    public Optional<Student_DTO> getStudentById(Long studentId) {
        return studentRepository.findById(studentId).map(studentsEntity -> modelMapper.map(studentsEntity, Student_DTO.class));
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


    public Student_DTO updateStudent(Long studentId, Student_DTO studentsDto) {
        isExistByID(studentId);
        Student_Entity students = modelMapper.map(studentsDto, Student_Entity.class);
        students.setStudent_Id(studentId);
        Student_Entity updatedStudent = studentRepository.save(students);
        return modelMapper.map(updatedStudent, Student_DTO.class);
    }

    public boolean deleteStudentById(Long studentId) {
        isExistByID(studentId) ;
        studentRepository.deleteById(studentId);
        return true;
    }

    public Student_DTO partialUpdateStudentById(Long studentId, Map<String, Object> updates) {
        isExistByID(studentId);
        Student_Entity students = studentRepository.findById(studentId).get();

        updates.forEach((field, value) -> {
            Field fieldToUpdate = findRequiredField(Student_Entity.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, students, value);});

        Student_Entity updateRequiredField = studentRepository.save(students);
        return modelMapper.map(updateRequiredField,Student_DTO.class);

    }
}
