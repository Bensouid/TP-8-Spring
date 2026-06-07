package ma.fst.studentapi.mapper;

import ma.fst.studentapi.dto.CoursRequestDTO;
import ma.fst.studentapi.dto.CoursResponseDTO;
import ma.fst.studentapi.entity.Cours;
import org.springframework.stereotype.Component;


@Component
public class CoursMapper {

    public Cours toEntity(CoursRequestDTO dto) {
        Cours cours = new Cours();
        cours.setTitre(dto.titre());
        cours.setDescription(dto.description());
        cours.setDateDebut(dto.dateDebut());
        cours.setDateFin(dto.dateFin());
        return cours;
    }

    public CoursResponseDTO toResponseDTO(Cours cours) {
 //     //  List<EtudiantSummaryDTO> etudiants = cours.getEtudiants().stream()
 //               .map(s -> new EtudiantSummaryDTO(s.getId(), s.getPrenom(), s.getNom()))
 //               .toList();
        return new CoursResponseDTO(
                cours.getId(),
                cours.getTitre(),
                cours.getDescription(),
                cours.getDateDebut(),
                cours.getDateFin()
  //              etudiants//
        );
    }

    public void updateEntityFromDTO(CoursRequestDTO dto, Cours cours) {
        cours.setTitre(dto.titre());
        cours.setDescription(dto.description());
        cours.setDateDebut(dto.dateDebut());
        cours.setDateFin(dto.dateFin());
    }
}
