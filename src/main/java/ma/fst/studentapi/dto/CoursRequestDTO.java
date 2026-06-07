package ma.fst.studentapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CoursRequestDTO(

        @NotBlank(message = "Le titre du cours est obligatoire")
        String titre,

        String description,

        @NotNull(message = "La date de début est obligatoire")
        LocalDate dateDebut,

        @NotNull(message = "La date de fin est obligatoire")
        LocalDate dateFin
) {
}
