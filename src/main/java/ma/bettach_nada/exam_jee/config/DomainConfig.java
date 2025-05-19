package ma.bettach_nada.exam_jee.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("ma.bettach_nada.exam_jee.Entities")
@EnableJpaRepositories("ma.bettach_nada.exam_jee.repos")
@EnableTransactionManagement
public class DomainConfig {
}
