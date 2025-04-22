package SpringBoot.College_Management.Students.Student_Details;

//import SpringBoot.College_Management.Security_Section.Owner_Details.Owner_Of_Entity;
import SpringBoot.College_Management.Students.Student_DTO;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Students.Student_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class student_service {

    private final Student_Repository studentRepository;
    private final ModelMapper modelMapper;


    public List<Student> allStudents() {
        List<Student_Entity> students = studentRepository.findAll();
        return students
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, Student.class))
                .collect(Collectors.toList());
    }

    public Optional<Student> getStudentByIdAndName(String studentId, String studentName) {

            return studentRepository.findByStudentIdAndName(studentId,studentName).map(studentsEntity -> modelMapper.map(studentsEntity, Student.class));

    }
}
