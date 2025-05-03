package SpringBoot.College_Management.Courses;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Students.Student_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Course_Service {
    private final Course_Repository courseRepository;
    private final ModelMapper modelMapper;
    private final Student_Repository studentRepository;
//    private final Semester_Repository semesterRepository;

    public Course_DTO addNewCourse(Course_DTO courseDto) {
        Course_Entity course = modelMapper.map(courseDto,Course_Entity.class);

        long count = courseRepository.count() + 1;
        String customId = "COR_" + String.format("%d", count);
        course.setCourseId(customId);

        if (courseRepository.existsByCourse(course.getCourse())) {
            throw new RuntimeException("Course with "+course.getCourse()+" name is already exists");
        }
        Course_Entity toSaveCourse = courseRepository.save(course);
        return modelMapper.map(toSaveCourse,Course_DTO.class);
    }
    public Optional<Course_DTO> getCourseByName(String courseName) {
        return courseRepository.findByCourse(courseName).map(courseEntity -> modelMapper.map(courseEntity, Course_DTO.class));
    }

    public List<Course_DTO> getAllCourses() {
        List<Course_Entity> courseEntities = courseRepository.findAll();
        return courseEntities
                .stream()
                .map(course -> modelMapper.map(course,Course_DTO.class))
                .collect(Collectors.toList());

    }

    public void isExistByID(String courseName) {
        boolean isExist = courseRepository.existsByCourse(courseName);// checks the id is present or not
        if (!isExist)
            throw new ResourceNotFound("Course Not Found with name : " + courseName);
    }

    @Transactional
    public boolean deleteCourseById(String courseName) {
        isExistByID(courseName) ;
        courseRepository.deleteByCourse(courseName);
        return true;
    }

}
