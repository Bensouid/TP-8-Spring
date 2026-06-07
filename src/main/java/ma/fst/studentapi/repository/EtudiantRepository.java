package ma.fst.studentapi.repository;

import ma.fst.studentapi.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Optional<Etudiant> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Etudiant> findByCours_Id(Long coursId);
}