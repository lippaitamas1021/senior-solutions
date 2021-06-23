package movie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieServiceTest {

    MovieService movieService = new MovieService();

    @BeforeEach
    public void init() {
        movieService.save(new Movie("Üvegtigris1", 105, LocalDate.of(2001, 10, 18)));
        movieService.save(new Movie("Üvegtigris2", 103, LocalDate.of(2006, 1, 19)));
        movieService.save(new Movie("Üvegtigris3", 107, LocalDate.of(2010, 12, 16)));
    }

    @Test
    void saveTest() {
        movieService.save(new Movie("A tanú", 112, LocalDate.of(1969, 10, 21)));
        Assertions.assertTrue(movieService.getMovies().contains(new Movie("A tanú", 112, LocalDate.of(1969, 10, 21))));
    }

    @Test
    void getLatestMovieTest() {
        assertEquals("Üvegtigris3", movieService.getLatestMovie().getName());
    }

    @Test
    void searchByPartOfTitleTest() {
        List<Movie> result = movieService.searchByPartOfTitle("veg");
        assertEquals(3, result.size());
        assertEquals("Üvegtigris1", result.get(0).getName());
    }
}
