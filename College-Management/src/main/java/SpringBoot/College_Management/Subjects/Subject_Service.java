package SpringBoot.College_Management.Subjects;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Professors.Professor_Entity;
import SpringBoot.College_Management.Professors.Professor_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Subject_Service {

    private final Subject_Repository subjectRepository;
    private final Professor_Repository professorRepository;
    private final ModelMapper modelMapper;

    public void isExistByID(Long subjectId) {
        boolean isExist = subjectRepository.existsById(subjectId);// checks the id is present or not
        if (!isExist)
            throw new ResourceNotFound("Employee Not Found with Id : " + subjectId);
    }

    public List<Subject_DTO> getAllSubjects() {
        List<Subject_Entity> subjects = subjectRepository.findAll();
        return subjects.stream()
                .map(subjectEntity -> modelMapper.map(subjectEntity, Subject_DTO.class))
                .collect(Collectors.toList());
    }


    public Optional<Subject_DTO> getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId).map(subjectEntity -> modelMapper.map(subjectEntity, Subject_DTO.class));
    }


    public Subject_DTO addNewSubject(Subject_DTO subjectDto) {
        Subject_Entity subjects = modelMapper.map(subjectDto, Subject_Entity.class);
        if (subjectRepository.existsByName(subjects.getName())) {
            throw new RuntimeException(subjects.getName()+" subject is already exist");
        }
        Subject_Entity savedSubjects = subjectRepository.save(subjects);
        return modelMapper.map(savedSubjects, Subject_DTO.class);
    }


    public Subject_DTO updateSubject(Long subjectId, Subject_DTO subjectDto) {
        isExistByID(subjectId);
        Subject_Entity subject = modelMapper.map(subjectDto, Subject_Entity.class);
        subject.setSubject_Id(subjectId);
        Subject_Entity updatedSubject = subjectRepository.save(subject);
        return modelMapper.map(updatedSubject, Subject_DTO.class);
    }

    public boolean deleteSubjectById(Long subjectId) {
        isExistByID(subjectId);
        subjectRepository.deleteById(subjectId);
        return true;
    }

}
