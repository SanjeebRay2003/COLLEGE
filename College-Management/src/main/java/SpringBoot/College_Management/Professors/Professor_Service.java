package SpringBoot.College_Management.Professors;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Subjects.Subject_Entity;
import SpringBoot.College_Management.Subjects.Subject_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.util.ReflectionUtils.findRequiredField;

@Service
@RequiredArgsConstructor
public class Professor_Service {

    private final Professor_Repository professorRepository;
    private final ModelMapper modelMapper;
    private final Subject_Repository subjectRepository;

    public void isExistByIdAndName(Long professorId,String professorName) {
        boolean isExistById = professorRepository.existsById(professorId);// checks the id is present or not
        if (!isExistById)
            throw new ResourceNotFound("Professor Not Found with id : " + professorId);
        boolean isExistByName = professorRepository.existsByName(professorName);
        if (!isExistByName)
            throw new ResourceNotFound("Professor Not Found with name : " + professorName);
    }

    public Optional<Professor_DTO> getProfessorByName(Long professorId,String professorName) {
        return professorRepository.findByProfessorIdOrName(professorId, professorName).map(professorEntity -> modelMapper.map(professorEntity, Professor_DTO.class));
    }

//    public Optional<Professor_DTO> getProfessorById(Long professorId) {
//        return professorRepository.findById(professorId).map(professorEntity -> modelMapper.map(professorEntity, Professor_DTO.class));
//    }


    public Professor_DTO addNewProfessor(Professor_DTO professorDto) {

        Professor_Entity professor = modelMapper.map(professorDto,Professor_Entity.class);
        if (professorRepository.existsByEmail(professor.getEmail())){
            throw new RuntimeException("Professor already exist with email "+ professor.getEmail());
        }
        if (professorRepository.existsByContactNo(professor.getContactNo())){
            throw new RuntimeException("Professor already exist with contact number "+ professor.getContactNo());
        }
        Professor_Entity savedProfessor = professorRepository.save(professor);
        return modelMapper.map(savedProfessor,Professor_DTO.class);

    }


    public List<Professor_DTO> getAllProfessors() {
        List<Professor_Entity>  professor = professorRepository.findAll();
        return professor
                .stream()
                .map(professorEntity -> modelMapper.map(professorEntity, Professor_DTO.class))
                .collect(Collectors.toList());
    }

    public Professor_DTO updateProfessor(Long professorId,String professorName,Professor_DTO professorDto) {
        isExistByIdAndName(professorId,professorName);
        Professor_Entity professorEntity = professorRepository.findByProfessorIdAndName(professorId,professorName).get();
        Professor_Entity professor = modelMapper.map(professorDto, Professor_Entity.class);
        professor.setProfessorId(professorId);
//        professor.setName(professorName);
        Professor_Entity updatedProfessor = professorRepository.save(professor);
        return modelMapper.map(updatedProfessor, Professor_DTO.class);
    }

    @Transactional
    public boolean deleteProfessorByName(Long professorId,String professorName) {
        isExistByIdAndName(professorId,professorName);
        Professor_Entity professor = professorRepository.findByProfessorIdAndName(professorId,professorName).get();
        professorRepository.deleteByProfessorIdAndName(professorId, professorName);
        return true;
    }

    public Professor_DTO partialUpdateProfessorByName(Long professorId,String professorName, Map<String, Object> updates) {
        isExistByIdAndName(professorId,professorName);
        Professor_Entity professor = professorRepository.findByProfessorIdAndName(professorId,professorName).get();

        updates.forEach((field, value) -> {
            Field fieldToUpdate = findRequiredField(Professor_Entity.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, professor, value);});

        Professor_Entity updateRequiredField = professorRepository.save(professor);
        return modelMapper.map(updateRequiredField,Professor_DTO.class);

    }

    //ASSIGNING SUBJECTS TO PROFESSORS____________________________________________________________________________________________________________________________________

    public Professor_DTO assignSubjectsToProfessors(Long professorId,String professorName, String subjectName) {

        Optional<Professor_Entity> professorEntity = professorRepository.findByProfessorIdAndName(professorId, professorName);
        Optional<Subject_Entity> subjectEntity = subjectRepository.findBySubject(subjectName);

        return professorEntity.flatMap(professor -> subjectEntity.map(
                subject -> {
                    subject.getProfessors().add(professor);
                    subjectRepository.save(subject);
                    professor.getSubjects().add(subject);
                    professorRepository.save(professor);
                    return modelMapper.map(professor, Professor_DTO.class);
                }
        )).orElse(null);
    }

}

