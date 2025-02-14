package SpringBoot.College_Management.Departments;

import SpringBoot.College_Management.Courses.Course_Entity;
import SpringBoot.College_Management.Courses.Course_Repository;
import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Students.Student_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        if (departmentRepository.existsByName(departmentEntity.getName())) {
            throw new RuntimeException("Department with "+departmentEntity.getName()+" name is already exists");
        }
//        if (departmentRepository.existsByCourse(departmentEntity.getName())) {
//            throw new RuntimeException("Course with "+departmentEntity.getName()+" name is already exists in other department");
//        }
        Department_Entity saveDepartment = departmentRepository.save(departmentEntity);
        return modelMapper.map(saveDepartment,Department_DTO.class);
    }

    public Optional<Department_DTO> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).map(departmentEntity -> modelMapper.map(departmentEntity, Department_DTO.class));
    }

    public List<Department_DTO> getAllDepartments() {
        List<Department_Entity> departmentEntity = departmentRepository.findAll();
        return departmentEntity
                .stream()
                .map(departments -> modelMapper.map(departments,Department_DTO.class))
                .collect(Collectors.toList());

    }



    public void isExistByID(Long departmentId) {
        boolean isExist = departmentRepository.existsById(departmentId);// checks the id is present or not
        if (!isExist)
            throw new ResourceNotFound("Deparment Not Found with Id : " + departmentId);
    }

    public boolean deleteDepartmentById(Long departmentId) {
        isExistByID(departmentId) ;
        departmentRepository.deleteById(departmentId);
        return true;
    }


// ASSIGNING DEPARTMENT TO STUDENTS___________________________________________________________________________________________________________________________________________________________________

//    public Department_DTO assignDepartmentToStudents(Long departmentId, Long studentId) {
//
//        Optional<Department_Entity> departmentEntity = departmentRepository.findById(departmentId);
//        Optional<Student_Entity> studentEntity = studentRepository.findById(studentId);
//
//        return departmentEntity.flatMap(department -> studentEntity.map(
//                student -> {
//                    student.setDepartment(department);
//                    studentRepository.save(student);
//                    department.getStudents().add(student);
//                    departmentRepository.save(department);
//                   return modelMapper.map(department,Department_DTO.class);
//                }
//        )).orElse(null);
//
//    }

    public Department_DTO assignCoursesToDepartment(Long departmentId, Long courseId) {
        Optional<Department_Entity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<Course_Entity> courseEntity = courseRepository.findById(courseId);

        return departmentEntity.flatMap(department -> courseEntity.map(
                course -> {
                    course.setDepartment_entity(department);
                    courseRepository.save(course);
                    department.getCourse().add(course);

                    return modelMapper.map(department,Department_DTO.class);
                }
        )).orElse(null);
    }

}
