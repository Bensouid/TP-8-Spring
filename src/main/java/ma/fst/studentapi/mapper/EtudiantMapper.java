package ma.fst.studentapi.mapper;

import ma.fst.studentapi.dto.EtudiantRequestDTO;
import ma.fst.studentapi.dto.EtudiantResponseDTO;
import ma.fst.studentapi.entity.Etudiant;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {

    public Etudiant toEntity(EtudiantRequestDTO dto) {
        Etudiant etudiant = new Etudiant();
        etudiant.setPrenom(dto.prenom());
        etudiant.setNom(dto.nom());
        etudiant.setEmail(dto.email());
        etudiant.setFiliere(dto.filiere());
        etudiant.setAge(dto.age());
        return etudiant;
    }

    public EtudiantResponseDTO toResponseDTO(Etudiant etudiant) {
        Long coursId = etudiant.getCours() != null ? etudiant.getCours().getId() : null;
        String coursTitre = etudiant.getCours() != null ? etudiant.getCours().getTitre() : null;
        return new EtudiantResponseDTO(
                etudiant.getId(),
                etudiant.getPrenom(),
                etudiant.getNom(),
                etudiant.getEmail(),
                etudiant.getFiliere(),
                etudiant.getAge(),
                coursId,
                coursTitre
        );
    }

    public void updateEntityFromDTO(EtudiantRequestDTO dto, Etudiant etudiant) {
        etudiant.setPrenom(dto.prenom());
        etudiant.setNom(dto.nom());
        etudiant.setEmail(dto.email());
        etudiant.setFiliere(dto.filiere());
        etudiant.setAge(dto.age());
    }
}