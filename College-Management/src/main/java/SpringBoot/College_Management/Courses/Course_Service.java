package SpringBoot.College_Management.Courses;

import SpringBoot.College_Management.Departments.Department_DTO;
import SpringBoot.College_Management.Departments.Department_Entity;
import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Professors.Professor_DTO;
import SpringBoot.College_Management.Semesters.Semester_Entity;
import SpringBoot.College_Management.Semesters.Semester_Repository;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Students.Student_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Course_Service {
    private final Course_Repository courseRepository;
    private final ModelMapper modelMapper;
    private final Student_Repository studentRepository;
    private final Semester_Repository semesterRepository;

    public Course_DTO addNewCourse(Course_DTO courseDto) {
        Course_Entity course = modelMapper.map(courseDto,Course_Entity.class);
        if (courseRepository.existsByName(course.getName())) {
            throw new RuntimeException("Course with "+course.getName()+" name is already exists");
        }
        Course_Entity toSaveCourse = courseRepository.save(course);
        return modelMapper.map(toSaveCourse,Course_DTO.class);
    }
    public Optional<Course_DTO> getCourseById(Long courseId) {
        return courseRepository.findById(courseId).map(courseEntity -> modelMapper.map(courseEntity, Course_DTO.class));
    }

    public List<Course_DTO> getAllCourses() {
        List<Course_Entity> courseEntities = courseRepository.findAll();
        return courseEntities
                .stream()
                .map(course -> modelMapper.map(course,Course_DTO.class))
                .collect(Collectors.toList());

    }

    public void isExistByID(Long courseId) {
        boolean isExist = courseRepository.existsById(courseId);// checks the id is present or not
        if (!isExist)
            throw new ResourceNotFound("Deparment Not Found with Id : " + courseId);
    }

    public boolean deleteCourseById(Long courseId) {
        isExistByID(courseId) ;
        courseRepository.deleteById(courseId);
        return true;
    }


    // ASSIGNING STUDENTS TO COURSES_________________________________________________________________________________________________________________________

    public Course_DTO assignCourseToStudents(Long courseId, Long studentId) {

        Optional<Course_Entity> courseEntity = courseRepository.findById(courseId);
        Optional<Student_Entity> studentEntity = studentRepository.findById(studentId);

//        if (courseRepository.existsByStudentId(studentId)){
//            throw new RuntimeException("Student with id "+studentId+" already exist in other course");
//        }

        return courseEntity.flatMap(course -> studentEntity.map(
                student -> {
                    student.setCourse(course);
                    studentRepository.save(student);
                    course.getStudents().add(student);
                    courseRepository.save(course);
                    return modelMapper.map(course,Course_DTO.class);
                }
        )).orElse(null);
    }

    // ASSIGNING SEMESTERS TO COURSES _________________________________________________________________________________________________________________________

    public Course_DTO assignSemestersToCourse(Long courseId, Long semesterId) {

        Optional<Course_Entity> courseEntity = courseRepository.findById(courseId);
        Optional<Semester_Entity> semesterEntity = semesterRepository.findById(semesterId);

        return courseEntity.flatMap(course -> semesterEntity.map(
                semester -> {
                    semester.getCourses().add(course);
                    semesterRepository.save(semester);
                    course.getSemesters().add(semester);
                    courseRepository.save(course);
                    return modelMapper.map(course, Course_DTO.class);
                }
        )).orElse(null);
    }

}
