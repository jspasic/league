package rs.jspasic.league;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LeagueApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeagueApplication.class, args);
	}

}
