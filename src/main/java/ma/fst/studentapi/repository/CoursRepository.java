package ma.fst.studentapi.repository;

import ma.fst.studentapi.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CoursRepository extends JpaRepository<Cours, Long> {

    List<Cours> findByDateDebutGreaterThanEqualAndDateFinLessThanEqual(LocalDate dateDebut, LocalDate dateFin);
}