package movie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieControllerTest {

    @Autowired
    MovieController movieController = new MovieController(new MovieService());

    @Test
    void listMovie() {
        Assertions.assertTrue(movieController.listMovie().contains(new Movie("Üvegtigris1", 105, LocalDate.of(2001, 10, 21))));
    }
}