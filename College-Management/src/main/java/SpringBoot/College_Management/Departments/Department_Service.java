package SpringBoot.College_Management.Departments;

import SpringBoot.College_Management.Courses.Course_Entity;
import SpringBoot.College_Management.Courses.Course_Repository;
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
public class Department_Service {

    private final Department_Repository departmentRepository;
    private final ModelMapper modelMapper;
    private final Student_Repository studentRepository;
    private final Course_Repository courseRepository;


    public Department_DTO addNewDepartment(Department_DTO department) {
        Department_Entity departmentEntity = modelMapper.map(department,Department_Entity.class);
        if (departmentRepository.existsByDepartment(departmentEntity.getDepartment())) {
            throw new RuntimeException("Department with "+departmentEntity.getDepartment()+" name is already exists");
        }
        Department_Entity saveDepartment = departmentRepository.save(departmentEntity);
        return modelMapper.map(saveDepartment,Department_DTO.class);
    }

    public Optional<Department_DTO> getDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartment(departmentName).map(departmentEntity -> modelMapper.map(departmentEntity, Department_DTO.class));
    }

    public List<Department_DTO> getAllDepartments() {
        List<Department_Entity> departmentEntity = departmentRepository.findAll();
        return departmentEntity
                .stream()
                .map(departments -> modelMapper.map(departments,Department_DTO.class))
                .collect(Collectors.toList());

    }



    public void isExistByID(String departmentName) {
        boolean isExist = departmentRepository.existsByDepartment(departmentName);// checks the department is present or not
        if (!isExist)
            throw new ResourceNotFound("Department Not Found with name : " + departmentName);
    }

    @Transactional
    public boolean deleteDepartmentByName(String departmentName) {
        isExistByID(departmentName) ;
        departmentRepository.deleteByDepartment(departmentName);
        return true;
    }

// ASSIGNING COURSES TO DEPARTMENT---------------------------------------------------------------------------------------------------------------------------------

    public Department_DTO assignCoursesToDepartment(String departmentName, String courseName) {
        Optional<Department_Entity> departmentEntity = departmentRepository.findByDepartment(departmentName);
        Optional<Course_Entity> courseEntity = courseRepository.findByCourse(courseName);


        if (departmentRepository.existsByCourse(courseEntity.get())){
            throw new RuntimeException("Course with name "+ courseName + " is already exists in other department");
        }


        return departmentEntity.flatMap(department -> courseEntity.map(
                course -> {
                    course.setDepartment(department);
                    courseRepository.save(course);
                    department.getCourse().add(course);

                    return modelMapper.map(department,Department_DTO.class);
                }
        )).orElse(null);
    }

}
