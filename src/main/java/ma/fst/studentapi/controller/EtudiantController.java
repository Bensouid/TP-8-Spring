package ma.fst.studentapi.controller;

import jakarta.validation.Valid;
import ma.fst.studentapi.dto.EtudiantRequestDTO;
import ma.fst.studentapi.dto.EtudiantResponseDTO;
import ma.fst.studentapi.service.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EtudiantResponseDTO ajouterEtudiant(@Valid @RequestBody EtudiantRequestDTO dto) {
        return etudiantService.ajouterEtudiant(dto);
    }

    @GetMapping
    public List<EtudiantResponseDTO> getTousLesEtudiants() {
        return etudiantService.getTousLesEtudiants();
    }

    @GetMapping("/{id}")
    public EtudiantResponseDTO getEtudiantParId(@PathVariable Long id) {
        return etudiantService.getEtudiantParId(id);
    }

    @PutMapping("/{id}")
    public EtudiantResponseDTO mettreAJourEtudiant(@PathVariable Long id,
                                            @Valid @RequestBody EtudiantRequestDTO dto) {
        return etudiantService.mettreAJourEtudiant(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimerEtudiant(@PathVariable Long id) {
        etudiantService.supprimerEtudiant(id);
    }
}