package SpringBoot.College_Management.Professors.Sorted_Professors;

import SpringBoot.College_Management.Professors.Professor_DTO;
import SpringBoot.College_Management.Professors.Professor_Entity;
import SpringBoot.College_Management.Professors.Professor_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Sort_Professor_Service {

    private final Professor_Repository professorRepository;
    private final ModelMapper modelMapper;

    public List<Professor_DTO> getSortedProfessorByName(String name) {
        List<Professor_Entity> professor = professorRepository.findByName(name);
        return professor
                .stream()
                .map(professorEntity -> modelMapper.map(professorEntity, Professor_DTO.class))
                .collect(Collectors.toList());
    }

    public List<Professor_DTO> getSortedProfessorByIsActive(Boolean active) {
        List<Professor_Entity> professor = professorRepository.findByIsActive(active);
        return professor
                .stream()
                .map(professorEntity -> modelMapper.map(professorEntity, Professor_DTO.class))
                .collect(Collectors.toList());
    }

    public List<Professor_DTO> getSortedProfessorByDateOfAdmission(LocalDate start, LocalDate end) {
        List<Professor_Entity> professor = professorRepository.findByDateOfJoiningBetween(start,end);
        return professor
                .stream()
                .map(professorEntity -> modelMapper.map(professorEntity, Professor_DTO.class))
                .collect(Collectors.toList());
    }
}
