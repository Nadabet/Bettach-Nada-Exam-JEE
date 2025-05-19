package ma.bettach_nada.exam_jee.repos;

import ma.bettach_nada.exam_jee.Entities.Credit;
import ma.bettach_nada.exam_jee.Entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {

    Remboursement findFirstByCredit(Credit credit);

}
