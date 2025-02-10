package SpringBoot.College_Management.Courses;

import SpringBoot.College_Management.Departments.Department_DTO;
import SpringBoot.College_Management.Departments.Department_Entity;
import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Course_Service {
    private final Course_Repository courseRepository;
    private final ModelMapper modelMapper;

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
}
