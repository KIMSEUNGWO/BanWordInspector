package ban.inspector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class InspectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(InspectorApplication.class, args);
	}

}
