package locations;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    private LocationParser locationParser;
    String location1;
    String location2;
    String location3;

    @BeforeEach
    void init() {
        locationParser = new LocationParser();
        location1 = "Békéscsaba, 0, 72.913752";
        location2 = "Balmazújváros, 33.197256, 0";
        location3 = "Baja, 12.557326, 33.416238";
    }

    @Test
    void testParse() {
//        System.out.println(locationParser.parse(location2).toString());
//        System.out.println(locationParser.parse(location2).toString());
        assertAll(
                () -> assertEquals("Békéscsaba", locationParser.parse(location1).toString()),
                () -> assertEquals(33.197256, locationParser.parse(location2).getLat()),
                () -> assertEquals(33.416238, locationParser.parse(location3).getLon())
        );
    }

    @Test
    void isOnEquatorTest() {
        Location location = locationParser.parse(location1);
        assertEquals(true, location.isOnEquator());
    }

    @Test
    void isOnPrimeMeridianTest() {
        Location location = locationParser.parse(location2);
        assertEquals(true, location.isOnPrimeMeridian());
    }

    @Test
    void distanceFromTest() {
        Location location1 = new Location("Óhat-Pusztakücs", 47.62922, 20.93852);
        Location location2 = new Location("Beregdaróc", 48.19622, 22.54252);
        assertEquals(135.14843029121423, location1.distanceFrom(location2));
    }

    @Test
    void parseTestWithEveryParameter() {
        assertAll(
                () -> assertEquals("Baja", locationParser.parse(location3).getName()),
                () -> assertEquals(12.557326, locationParser.parse(location3).getLat()),
                () -> assertEquals(33.416238, locationParser.parse(location3).getLon())
        );
    }

    @Test
    void locationWithWrongParametersTest() {
        assertThrows(IllegalStateException.class, () -> new Location("Omaruru", -91.39969, 15.95855));
        assertThrows(IllegalStateException.class, () -> new Location("Alalay", -17.68028, -181.68785));
    }

    @RepeatedTest(value = 3, name = " isOnEquator {currentRepetition}/{totalRepetitions}")
    void testIsOnEquator(RepetitionInfo repInfo) {
        assertEquals(places[repInfo.getCurrentRepetition() - 1][1],
                ((Location) places[repInfo.getCurrentRepetition() - 1][0]).isOnEquator());
    }

    private Object[][] places = {
            {new Location("Óhat-Pusztakócs", 47.62603, 20.94815), false},
            {new Location("Sintang", 0, 111.48931), true},
            {new Location("Greenwich", 51.49340, 0), false}
    };

//    @ParameterizedTest(name = "data: {0} - isPrimeMeridian: {1}")
//    @MethodSource("createData")
//    void testIsOnPrimeMeridianMethodSource(Location location, Boolean b) {
//        assertEquals(b, location.isOnPrimeMeridian());
//    }
//
//    static Stream<Arguments> createData() {
//        return Stream.of(
//                Arguments.arguments(new Location("Sintang", 0, 111.48931), true),
//                Arguments.arguments(new Location("Greenwich", 51.49340, 0), false),
//                Arguments.arguments(new Location("Óhat-Pusztakócs", 47.62603, 20.94815), false)
//        );
//    }
//
//    @ParameterizedTest(name = "data: {0} - isPrimeMeridian: {1}")
//    @CsvFileSource(resources = "/data.csv")
//    void testCSVSource(String s1, double lat1, double lon1, String s2, double lat2, double lon2, double distance) {
//        Location l1 = new Location(s1, lat1, lon1);
//        Location l2 = new Location(s2, lat2, lon2);
//        assertEquals(distance, l1.distanceFrom(l2), 0.01);
//    }

    @TestFactory
    Stream<DynamicTest> testDynamic() {
        return Stream.of(new String[][]{{"Sintang, 0, 111.48931, false"}, {"Greenwich, 51.49340, 0,, false"}, {"Óhat-Pusztakócs, 47.62603, 20.94815, false"}})
                .map(loc -> DynamicTest.dynamicTest("is on Equator: " + loc[1] +": "+ loc[0],
                        () -> assertEquals(Boolean.parseBoolean(loc[1]), locationParser.parse(loc[0]).isOnEquator())
                ));
    }

    @TempDir
    Path tempDir;
    @Test
    void testWriteLocation() throws IOException {
        Path file = tempDir.resolve("places.csv");
        List<Location> locationList = List.of(
                new Location("Gödöllő", 47.59762, 19.37254),
                new Location("Dömsöd", 47.09329, 18.99077),
                new Location("Pretoria", -25.39891, 27.89274));
        new LocationService().writeLocations(file,locationList);

        assertEquals("Gödöllő, 47.59762, 19.37254\n" +
                "Dömsöd, 47.09329, 18.99077\n" +
                "Pretoria, -25.39891, 27.89274\n", Files.readString(file));
    }
}
