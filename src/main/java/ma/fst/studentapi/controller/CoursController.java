package ma.fst.studentapi.controller;

import jakarta.validation.Valid;
import ma.fst.studentapi.dto.CoursRequestDTO;
import ma.fst.studentapi.dto.CoursResponseDTO;
import ma.fst.studentapi.dto.EtudiantSummaryDTO;
import ma.fst.studentapi.service.CoursService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class CoursController {

    private final CoursService coursService;

    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CoursResponseDTO createCours(@Valid @RequestBody CoursRequestDTO dto) {
        return coursService.createCours(dto);
    }

    @GetMapping
    public List<CoursResponseDTO> getAllCours() {
        return coursService.getAllCours();
    }

    @GetMapping("/{id}")
    public CoursResponseDTO getCoursById(@PathVariable Long id) {
        return coursService.getCoursById(id);
    }

    @PutMapping("/{id}")
    public CoursResponseDTO updateCours(@PathVariable Long id,
                                        @Valid @RequestBody CoursRequestDTO dto) {
        return coursService.updateCours(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCours(@PathVariable Long id) {
        coursService.deleteCours(id);
    }

    @PostMapping("/{coursId}/etudiants/{etudiantId}")
    public CoursResponseDTO inscrireEtudiant(@PathVariable Long coursId,
                                          @PathVariable Long etudiantId) {
        return coursService.inscrireEtudiant(coursId, etudiantId);
    }

    @DeleteMapping("/{coursId}/etudiants/{etudiantId}")
    public CoursResponseDTO uninscrireEtudiant(@PathVariable Long coursId,
                                            @PathVariable Long etudiantId) {
        return coursService.uninscrireEtudiant(coursId, etudiantId);
    }

    @GetMapping("/{id}/etudiants")
    public List<EtudiantSummaryDTO> obtenirEtudiantsParCours(@PathVariable Long id) {
        return coursService.obtenirEtudiantsParCours(id);
    }

    @GetMapping("/disponibles")
    public List<CoursResponseDTO> getCoursDisponibles(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        return coursService.getCoursDisponibles(dateDebut, dateFin);
    }
}