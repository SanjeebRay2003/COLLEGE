package SpringBoot.College_Management.Students.Sorted_Students;

import SpringBoot.College_Management.Courses.Course_Repository;
import SpringBoot.College_Management.Students.Student_DTO;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Students.Student_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Sort_Students_Service {
    private final Student_Repository studentRepository;
    private final ModelMapper modelMapper;
    private final Course_Repository courseRepository;

    public List<Student_DTO> getSortedStudentBySemester(String semester) {
        List<Student_Entity> student = studentRepository.findBySemester(semester);
       return student
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, Student_DTO.class))
                .collect(Collectors.toList());
    }

    public List<Student_DTO> getSortedStudentByName(String name) {
        List<Student_Entity> student = studentRepository.findByName(name);
        return student
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, Student_DTO.class))
                .collect(Collectors.toList());
    }

    public List<Student_DTO> getSortedStudentByIsActive(Boolean active) {
        List<Student_Entity> student = studentRepository.findByIsActive(active);
        return student
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, Student_DTO.class))
                .collect(Collectors.toList());
    }

    public List<Student_DTO> getSortedStudentByDateOfAdmission(LocalDate start, LocalDate end) {
        List<Student_Entity> student = studentRepository.findByDateOfAdmissionBetween(start,end);
        return student
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, Student_DTO.class))
                .collect(Collectors.toList());
    }

    public List<Student_DTO> getSortedStudentByRollNo(String start, String end) {
        List<Student_Entity> student = studentRepository.findByRollNoBetween(start,end);
        return student
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, Student_DTO.class))
                .collect(Collectors.toList());
    }
}
