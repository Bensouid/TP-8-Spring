package ma.fst.studentapi.service;

import ma.fst.studentapi.dto.CoursRequestDTO;
import ma.fst.studentapi.dto.CoursResponseDTO;
import ma.fst.studentapi.dto.EtudiantSummaryDTO;
import ma.fst.studentapi.entity.Cours;
import ma.fst.studentapi.entity.Etudiant;
import ma.fst.studentapi.exception.ResourceNotFoundException;
import ma.fst.studentapi.mapper.CoursMapper;
import ma.fst.studentapi.repository.CoursRepository;
import ma.fst.studentapi.repository.EtudiantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CoursService {

    private final CoursRepository coursRepository;
    private final EtudiantRepository etudiantRepository;
    private final CoursMapper coursMapper;

    public CoursService(CoursRepository coursRepository,
                        EtudiantRepository etudiantRepository,
                        CoursMapper coursMapper) {
        this.coursRepository = coursRepository;
        this.etudiantRepository = etudiantRepository;
        this.coursMapper = coursMapper;
    }

    public CoursResponseDTO createCours(CoursRequestDTO dto) {
        Cours cours = coursMapper.toEntity(dto);
        return coursMapper.toResponseDTO(coursRepository.save(cours));
    }

    @Transactional(readOnly = true)
    public List<CoursResponseDTO> getAllCours() {
        return coursRepository.findAll().stream()
                .map(coursMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CoursResponseDTO getCoursById(Long id) {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cours introuvable avec l'id : " + id));
        return coursMapper.toResponseDTO(cours);
    }

    public CoursResponseDTO updateCours(Long id, CoursRequestDTO dto) {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cours introuvable avec l'id : " + id));
        coursMapper.updateEntityFromDTO(dto, cours);
        return coursMapper.toResponseDTO(coursRepository.save(cours));
    }

    public void deleteCours(Long id) {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cours introuvable avec l'id : " + id));
        cours.getEtudiants().forEach(e -> e.setCours(null));
        coursRepository.delete(cours);
    }

    public CoursResponseDTO inscrireEtudiant(Long coursId, Long etudiantId) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new ResourceNotFoundException("Cours introuvable avec l'id : " + coursId));
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant introuvable avec l'id : " + etudiantId));
        etudiant.setCours(cours);
        etudiantRepository.save(etudiant);
        return coursMapper.toResponseDTO(coursRepository.findById(coursId).orElseThrow());
    }

    public CoursResponseDTO uninscrireEtudiant(Long coursId, Long etudiantId) {
        coursRepository.findById(coursId)
                .orElseThrow(() -> new ResourceNotFoundException("Cours introuvable avec l'id : " + coursId));
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant introuvable avec l'id : " + etudiantId));
        etudiant.setCours(null);
        etudiantRepository.save(etudiant);
        return coursMapper.toResponseDTO(coursRepository.findById(coursId).orElseThrow());
    }

    @Transactional(readOnly = true)
    public List<EtudiantSummaryDTO> obtenirEtudiantsParCours(Long coursId) {
        coursRepository.findById(coursId)
                .orElseThrow(() -> new ResourceNotFoundException("Cours introuvable avec l'id : " + coursId));
        return etudiantRepository.findByCours_Id(coursId).stream()
                .map(s -> new EtudiantSummaryDTO(s.getId(), s.getPrenom(), s.getNom()))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CoursResponseDTO> getCoursDisponibles(LocalDate dateDebut, LocalDate dateFin) {
        return coursRepository.findByDateDebutGreaterThanEqualAndDateFinLessThanEqual(dateDebut, dateFin)
                .stream()
                .map(coursMapper::toResponseDTO)
                .toList();
    }
}