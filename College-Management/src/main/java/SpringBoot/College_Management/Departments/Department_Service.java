package SpringBoot.College_Management.Departments;

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
//    private final Student_Repository studentRepository;


    public Department_DTO addNewDepartment(Department_DTO department) {
        Department_Entity departmentEntity = modelMapper.map(department,Department_Entity.class);
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


}