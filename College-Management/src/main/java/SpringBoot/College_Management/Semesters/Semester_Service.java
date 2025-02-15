package SpringBoot.College_Management.Semesters;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Students.Student_DTO;
import SpringBoot.College_Management.Students.Student_Entity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Semester_Service {

    private final Semester_Repository semesterRepository;
    private final ModelMapper modelMapper;

    public Optional<Semester_DTO> getSemesterById(Long semesterId) {
        return semesterRepository.findById(semesterId).map(semesterEntity -> modelMapper.map(semesterEntity, Semester_DTO.class));
    }

    public List<Semester_DTO> getAllSemester() {
        List<Semester_Entity> semester = semesterRepository.findAll();
        return semester
                .stream()
                .map(semesterEntity -> modelMapper.map(semesterEntity, Semester_DTO.class))
                .collect(Collectors.toList());
    }

    public void isExistByID(Long semesterId) {
        boolean isExist = semesterRepository.existsById(semesterId);// checks the id is present or not
        if (!isExist)
            throw new ResourceNotFound("Student Not Found with Id : " + semesterId);
    }

    public boolean deleteSemesterById(Long semesterId) {
            isExistByID(semesterId) ;
            semesterRepository.deleteById(semesterId);
            return true;
    }

    public Semester_DTO addNewSemester(Semester_DTO semesterDto) {
            Semester_Entity semester = modelMapper.map(semesterDto, Semester_Entity.class);

            if (semesterRepository.existsBySemester(semester.getSemester())){
                throw new RuntimeException("Semester already exist with Number "+ semester.getSemester());
            }

            Semester_Entity savedSemester = semesterRepository.save(semester);

            return modelMapper.map(savedSemester, Semester_DTO.class);

    }
}
