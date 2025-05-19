package ma.bettach_nada.exam_jee.repos;

import ma.bettach_nada.exam_jee.Entities.Client;
import ma.bettach_nada.exam_jee.Entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditRepository extends JpaRepository<Credit, Long> {

    Credit findFirstByClient(Client client);

}
