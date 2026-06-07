package ma.fst.studentapi.dto;

import jakarta.validation.constraints.*;

public record EtudiantRequestDTO(

        @NotBlank(message = "Le prénom est obligatoire")
        String prenom,

        @NotBlank(message = "Le nom est obligatoire")
        String nom,

        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "Format d'email invalide")
        String email,

        @NotBlank(message = "La filière est obligatoire")
        String filiere,

        @NotNull(message = "L'âge est obligatoire")
        @Min(value = 17, message = "L'âge minimal est 17")
        @Max(value = 100, message = "L'âge maximal est 100")
        Integer age,

        Long coursId
) {
}