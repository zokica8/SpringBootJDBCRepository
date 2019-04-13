package kurs.spring.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootJdbcRepositoryWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcRepositoryWebApplication.class, args);
	}

}
