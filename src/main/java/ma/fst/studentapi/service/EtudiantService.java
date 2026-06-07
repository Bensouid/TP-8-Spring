package ma.fst.studentapi.service;

import ma.fst.studentapi.dto.EtudiantRequestDTO;
import ma.fst.studentapi.dto.EtudiantResponseDTO;
import ma.fst.studentapi.entity.Cours;
import ma.fst.studentapi.entity.Etudiant;
import ma.fst.studentapi.exception.ResourceNotFoundException;
import ma.fst.studentapi.mapper.EtudiantMapper;
import ma.fst.studentapi.repository.CoursRepository;
import ma.fst.studentapi.repository.EtudiantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final CoursRepository coursRepository;
    private final EtudiantMapper etudiantMapper;

    public EtudiantService(EtudiantRepository etudiantRepository,
                          CoursRepository coursRepository,
                          EtudiantMapper etudiantMapper) {
        this.etudiantRepository = etudiantRepository;
        this.coursRepository = coursRepository;
        this.etudiantMapper = etudiantMapper;
    }

    public EtudiantResponseDTO ajouterEtudiant(EtudiantRequestDTO dto) {
        Etudiant etudiant = etudiantMapper.toEntity(dto);
        if (dto.coursId() != null) {
            Cours cours = coursRepository.findById(dto.coursId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cours introuvable avec l'id : " + dto.coursId()));
            etudiant.setCours(cours);
        }
        return etudiantMapper.toResponseDTO(etudiantRepository.save(etudiant));
    }

    public List<EtudiantResponseDTO> getTousLesEtudiants() {
        return etudiantRepository.findAll()
                .stream()
                .map(etudiantMapper::toResponseDTO)
                .toList();
    }

    public EtudiantResponseDTO getEtudiantParId(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant introuvable avec l'id : " + id));

        return etudiantMapper.toResponseDTO(etudiant);
    }

    public EtudiantResponseDTO mettreAJourEtudiant(Long id, EtudiantRequestDTO dto) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant introuvable avec l'id : " + id));

        etudiantMapper.updateEntityFromDTO(dto, etudiant);

        if (dto.coursId() != null) {
            Cours cours = coursRepository.findById(dto.coursId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cours introuvable avec l'id : " + dto.coursId()));
            etudiant.setCours(cours);
        } else {
            etudiant.setCours(null);
        }

        return etudiantMapper.toResponseDTO(etudiantRepository.save(etudiant));
    }

    public void supprimerEtudiant(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant introuvable avec l'id : " + id));

        etudiantRepository.delete(etudiant);
    }
}