package movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieJpaSolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieJpaSolutionApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().findAndRegisterModules();
	}

//    docker run -d -e MYSQL_DATABASE=movies -e MYSQL_USER=movies -e MYSQL_PASSWORD=movies -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3307:3306 --name movie-mariadb mariadb
}
