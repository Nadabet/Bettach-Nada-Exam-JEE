package ma.bettach_nada.exam_jee.repos;

import ma.bettach_nada.exam_jee.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
