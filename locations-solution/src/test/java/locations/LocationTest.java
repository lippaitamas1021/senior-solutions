package locations;

import org.junit.jupiter.api.*;

public class LocationTest {

    LocationParser locationParser;

    @BeforeEach
    void setUp() {
        locationParser = new LocationParser();
    }

    @Test
    @DisplayName("AppropriateLocation")
    void createLocationTest() {
        Location location = new Location("Greenwich", 51.49340, 0);
        Assertions.assertEquals("Greenwich", location.getName());
        Assertions.assertEquals(51.49340, location.getLat());
        Assertions.assertEquals(0, location.getLon());
    }

    @Test
    @DisplayName("ExceptionThrownParams")
    void createLocationWithWrongParams() {
        Exception ex = Assertions.assertThrows(IllegalStateException.class,
                () -> new Location("", -91.0, -179.0));
        Assertions.assertEquals("The lattitude must be between -90 & 90", ex.getMessage());
    }

    @Test
    @DisplayName("LocationParse")
    void parseTest() {
        Location location = locationParser.parse("Óhat-Pusztakócs, 47.62922, 20.93852");
        Assertions.assertEquals("Óhat-Pusztakócs", location.getName());
        Assertions.assertEquals(47.62922, location.getLat());
        Assertions.assertEquals(20.93852, location.getLon());
    }

    @Test
    @DisplayName("ExceptionThrownString")
    void parseWithWrongParamsTest() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> locationParser.parse("BBeregdaróc48.19622, 22.54252"));
        Assertions.assertEquals("Wrong parameters", ex.getMessage());
    }

    @Test
    @DisplayName("ExceptionThrown")
    void testParseWithWrongParams() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> locationParser.parse("Balmazújváros, latitude=33.197256, longitude=0"));
        Assertions.assertEquals("Wrong parameters", ex.getMessage());
    }

    @Test
    @DisplayName("EquatorTrueTest")
    void equatorTest() {
        Location location = new Location("Békéscsaba", 0, 72.913752);
        Assertions.assertTrue(locationParser.isOnEquator(location));
    }

    @Test
    @DisplayName("MeridianTest")
    void meridianTest() {
        Location location = new Location("Balmazújváros", 33.197256, 0);
        Assertions.assertTrue(locationParser.isOnPrimeMeridian(location));
    }


    @Test
    @DisplayName("SameLocations")
    void testTwoDifferentParsedObjects() {
        Location location1 = locationParser.parse("Óhat-Pusztakócs, 47.62922, 20.93852");
        Location location2 = locationParser.parse("Óhat-Pusztakócs, 47.62922, 20.93852");
        Assertions.assertTrue(location1.equals(location2));
    }

    @Test
    @DisplayName("DistanceTest")
    void testDistanceFrom() {
        Location location1 = new Location("Gödöllő", 47.59762, 19.37254);
        Location location2 = new Location("Dömsöd", 47.09329, 18.99077);
        Assertions.assertEquals(63.0, location1.distanceFrom(location2), 1.0);
    }
}
