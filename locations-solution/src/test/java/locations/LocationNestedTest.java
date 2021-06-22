package locations;

import org.junit.jupiter.api.*;

public class LocationNestedTest {

    private LocationParser locationParser;

    @BeforeEach
    void setUp() {
        locationParser = new LocationParser();
    }

    @Nested
    class FirstFavouriteLocation {

        Location favouriteLocation;

        @BeforeEach
        void setUp() {
            favouriteLocation = new Location("Equator", 0.0, 0.0);
        }

        @Test
        @DisplayName("Equator")
        void testIsOnEquator() {
            Assertions.assertTrue(locationParser.isOnEquator(favouriteLocation));
        }

        @Test
        @DisplayName("Meridian")
        void testIsOnPrimeMeridian() {
            Assertions.assertTrue(locationParser.isOnPrimeMeridian(favouriteLocation));
        }
    }

    @Nested
    class SecondFavouriteLocation {
        Location favouriteLocation;
        @BeforeEach
        void setUp() {
            favouriteLocation = new Location("BÓhat-Pusztakócs", 47.62603, 20.94815);
        }

        @Test
        @DisplayName("NotOnEquator")
        void testIsOnEquatorNotOnEquator() {
            Assertions.assertFalse(locationParser.isOnEquator(favouriteLocation));
        }

        @Test
        @DisplayName("NotOnMeridian")
        void testIsOnPrimeMeridianNotOnPrimeMeridian() {
            Assertions.assertFalse(locationParser.isOnPrimeMeridian(favouriteLocation));
        }
    }
}