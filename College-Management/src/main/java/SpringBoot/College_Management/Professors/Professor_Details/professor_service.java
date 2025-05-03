package SpringBoot.College_Management.Professors.Professor_Details;

import SpringBoot.College_Management.Professors.Professor_Entity;
import SpringBoot.College_Management.Professors.Professor_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class professor_service {

    private final Professor_Repository professorRepository;
    private final ModelMapper modelMapper;

    public List<Professor> allProfessors() {
        List<Professor_Entity> professor = professorRepository.findAll();
        return professor
                .stream()
                .map(professorEntity -> modelMapper.map(professorEntity, Professor.class))
                .collect(Collectors.toList());
    }


    public Optional<Professor> getProfessorByIdAndName(String professorId, String professorName) {

        return professorRepository.findByProfessorIdAndName(professorId,professorName).map(professorEntity -> modelMapper.map(professorEntity, Professor.class));

    }
}
