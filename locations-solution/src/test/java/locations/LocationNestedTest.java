package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationNestedTest {

    private LocationParser locationParser;

    @Nested
    class WithZero {

        @BeforeEach
        void init() {
            locationParser = new LocationParser();
        }

        @Test
        void testLocation() {
            Location temp = locationParser.parse("Equator, 0, 0");
            assertAll(
                    () -> assertTrue(temp.isOnEquator()),
                    () -> assertTrue(temp.isOnPrimeMeridian())
            );
        }
    }

    @Nested
    class WithParam {

        @BeforeEach
        void init() {
            locationParser = new LocationParser();
        }

        @Test
        void testLocation() {
            Location temp = locationParser.parse("Óhat-Pusztakócs, 47.62603, 20.94815");
            assertAll(
                    () -> assertFalse(temp.isOnEquator()),
                    () -> assertFalse(temp.isOnPrimeMeridian())
            );
        }
    }
}