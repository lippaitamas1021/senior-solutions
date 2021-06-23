package movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MoviesSolutionApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MoviesSolutionApplication.class, args);
		MovieController controller = ctx.getBean(MovieController.class);
		System.out.println(controller.listMovie());
		System.out.println("Hello, Spring!");
//		SpringApplication.run(MoviesSolutionApplication.class, args);
	}


}


