package SpringBoot.College_Management.Professors;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import SpringBoot.College_Management.Security_Section.USER.User_Entity;
import SpringBoot.College_Management.Security_Section.USER.User_Repository;
import SpringBoot.College_Management.Students.Student_Entity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.util.ReflectionUtils.findRequiredField;

@Service
@RequiredArgsConstructor
public class Professor_Service {

    private final Professor_Repository professorRepository;
    private final ModelMapper modelMapper;
    private final User_Repository userRepository;

    public void isExistByIdAndName(String professorId,String professorName) {
        boolean isExistById = professorRepository.existsByProfessorId(professorId);// checks the id is present or not
        if (!isExistById)
            throw new ResourceNotFound("Professor Not Found with id : " + professorId);
        boolean isExistByName = professorRepository.existsByName(professorName);
        if (!isExistByName)
            throw new ResourceNotFound("Professor Not Found with name : " + professorName);
    }

    public Optional<Professor_DTO> getProfessorByIdAndName(String professorId, String professorName) {
        Optional<User_Entity> userEntity = userRepository.findByStudentId(professorId);
        Optional<Professor_Entity> professorEntity = professorRepository.findByProfessorId(professorId);
        if (Objects.equals(userEntity.get().getProfessorId(), professorEntity.get().getProfessorId())) {
            return professorRepository.findByProfessorIdAndName(professorId, professorName).map(professorEntity1 -> modelMapper.map(professorEntity, Professor_DTO.class));
        }
        return null;
    }



    public Professor_DTO addNewProfessor(Professor_DTO professorDto) {

        Professor_Entity professor = modelMapper.map(professorDto,Professor_Entity.class);

        long count = professorRepository.count() + 1;
        String customId = "PROF" + String.format("%03d", count)+"RU";
        professor.setProfessorId(customId);

        if (professorRepository.existsByEmail(professor.getEmail())){
            throw new RuntimeException("Professor already exist with email "+ professor.getEmail());
        }
        if (professorRepository.existsByContactNo(professor.getContactNo())){
            throw new RuntimeException("Professor already exist with contact number "+ professor.getContactNo());
        }
        professor.setSecretCode(generateSecretCode());

        if (professorRepository.existsBySecretCode(professor.getSecretCode())){
            throw new RuntimeException("Professor already exist with this secret code "+ professor.getSecretCode());
        }
        Professor_Entity savedProfessor = professorRepository.save(professor);
        return modelMapper.map(savedProfessor,Professor_DTO.class);

    }


    public List<Professor_DTO> getAllProfessors(String sortBy) {
        List<Professor_Entity> professor = professorRepository.findBy(Sort.by(Sort.Direction.ASC, sortBy, "isActive", "professorId", "dateOfJoining"));

        return professor
                .stream()
                .map(professorEntity -> modelMapper.map(professorEntity, Professor_DTO.class))
                .collect(Collectors.toList());
    }

    public Professor_DTO updateProfessor(String professorId,String professorName,Professor_DTO professorDto) {
        isExistByIdAndName(professorId,professorName);
//        Professor_Entity professorEntity = professorRepository.findByProfessorIdAndName(professorId,professorName).get();
        Professor_Entity professor = modelMapper.map(professorDto, Professor_Entity.class);
        professor.setProfessorId(professorId);
        professor.setSecretCode(generateSecretCode());

        Optional<User_Entity> user = userRepository.findByEmail(professor.getEmail());
        if (user.isPresent()){
            user.get().setProfessorId(null);
            user.get().setSecretCode(null);
        }

        Optional<Professor_Entity> professorEntity = professorRepository.findByProfessorIdAndName(professorId,professorName);
        professor.setSubjects(professorEntity.get().getSubjects());

        Optional<Professor_Entity> existingEmail = professorRepository.findByEmail(professorDto.getEmail());
        if (existingEmail.isPresent() && !existingEmail.get().getProfessorId().equals(professor.getProfessorId())) {
            throw new RuntimeException("Email already exist for another Professor " + professorDto.getEmail()+ " -> "+existingEmail.get().getProfessorId());
        }


        Professor_Entity updatedProfessor = professorRepository.save(professor);
        return modelMapper.map(updatedProfessor, Professor_DTO.class);
    }

    @Transactional
    public boolean deleteProfessorByName(String professorId,String professorName) {
        isExistByIdAndName(professorId,professorName);
        Professor_Entity professor = professorRepository.findByProfessorIdAndName(professorId,professorName).get();
        professorRepository.deleteByProfessorIdAndName(professorId, professorName);
        return true;
    }

    public Professor_DTO partialUpdateProfessorByName(String professorId,String professorName, Map<String, Object> updates) {
        isExistByIdAndName(professorId,professorName);
        Professor_Entity professor = professorRepository.findByProfessorIdAndName(professorId,professorName).get();

        updates.forEach((field, value) -> {
            Field fieldToUpdate = findRequiredField(Professor_Entity.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, professor, value);});

        Optional<Professor_Entity> existingEmail = professorRepository.findByEmail(professor.getEmail());
        if (existingEmail.isPresent() && !existingEmail.get().getProfessorId().equals(professor.getProfessorId())) {
            throw new RuntimeException("Email already exist for another Professor " + professor.getEmail()+ " -> "+existingEmail.get().getProfessorId());
        }

        Professor_Entity updateRequiredField = professorRepository.save(professor);
        return modelMapper.map(updateRequiredField,Professor_DTO.class);

    }


    public String generateSecretCode(){
        Random random = new Random();
        int code = random.nextInt(10000000);
        return String.format("RU_PROF"+"%d",code);
    }


    public Optional<Professor_DTO> getAllDataByOwner(String professorId) {
        Optional<Professor_Entity> professorEntity = professorRepository.findByProfessorId(professorId);
        return Optional.ofNullable(modelMapper.map(professorEntity, Professor_DTO.class));
    }

    public Professor_DTO getProfessorByProfessorId(String professorId) {
        Optional<Professor_Entity> professor = professorRepository.findByProfessorId(professorId);
        return modelMapper.map(professor,Professor_DTO.class);
    }
}

