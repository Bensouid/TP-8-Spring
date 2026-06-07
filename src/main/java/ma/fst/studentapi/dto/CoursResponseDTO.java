package ma.fst.studentapi.dto;

import java.time.LocalDate;

public record CoursResponseDTO(
        Long id,
        String titre,
        String description,
        LocalDate dateDebut,
        LocalDate dateFin
        //   List<EtudiantSummaryDTO> etudiants
) {
}
