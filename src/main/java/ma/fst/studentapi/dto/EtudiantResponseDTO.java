package ma.fst.studentapi.dto;

public record EtudiantResponseDTO(
        Long id,
        String prenom,
        String nom,
        String email,
        String filiere,
        Integer age,
        Long coursId,
        String coursTitre
) {
}