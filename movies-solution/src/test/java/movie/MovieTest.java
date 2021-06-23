package movie;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {

    @Test
    void testCreateMovie(){
        Movie movie = new Movie("Üvegtigris", 105, LocalDate.of(2001, 10,18));
        assertEquals("Üvegtigris", movie.getName());
        assertEquals(105, movie.getLength());
        assertEquals(LocalDate.of(2001,10,18), movie.getReleaseDate());
    }
}
