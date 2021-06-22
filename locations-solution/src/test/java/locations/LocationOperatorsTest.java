package locations;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@LocationOperationsFeatureTest
class LocationOperatorsTest {

    private LocationParser locationParser;
    private LocationOperators locationOperators;
    private List<Location> locations;

    @BeforeEach
    void init() {
        locationParser = new LocationParser();
        locationOperators = new LocationOperators();
        locations = Arrays.asList(
                new Location("Gödöllő", 47.59762, 19.37254),
                new Location("Dömsöd", 47.09329, 18.99077),
                new Location("Mór", 47.37117, 18.19426),
                new Location("Pretoria", -25.39891, 27.89274),
                new Location("Sintang", 0,111.47510),
                new Location("Greenwich", 51.49340, 0)
        );
    }

    @Test
    @DisplayName("NorthTest")
    void filterOnNorth() {
        List<Location> nothernlocations = locationOperators.filterOnNorth(locations);
        assertAll(
                () -> assertEquals(4, nothernlocations.size()),
                () -> assertEquals("Mór", nothernlocations.get(2).getName()),
                () -> assertEquals(51.49340, nothernlocations.get(3).getLat()),
                () -> assertEquals(18.99077, nothernlocations.get(1).getLon())
        );
    }

    @DisplayName("RepeatedEquatorTest")
    @RepeatedTest(value = 5, name = "Is on Equator? {currentRepetition}/{totalRepetitions}")
    void equatorRepeatedTest(RepetitionInfo info) {
        boolean[] values = new boolean[]{false, false, false, false, true, false};
        Assertions.assertEquals(values[info.getCurrentRepetition() - 1], locationParser.isOnEquator(locations.get(info.getCurrentRepetition() - 1)));
    }

    @DisplayName("RepeatedMeridianTest")
    @RepeatedTest(value = 5, name = "Is on Prime Meridian? {currentRepetition}/{totalRepetitions}")
    void MeridianRepeatedTest(RepetitionInfo info) {
        boolean[] values = new boolean[]{false, false, false, false, false, true};
        Assertions.assertEquals(values[info.getCurrentRepetition() - 1], locationParser.isOnPrimeMeridian(locations.get(info.getCurrentRepetition() - 1)));
    }

    @DisplayName("ParameterizedMeridianTest")
    @ParameterizedTest
    @MethodSource("createArguments")
    void testIsOnPrimeMeridianParameterized(Location location, boolean isThere) {
        Assertions.assertEquals(isThere, locationParser.isOnPrimeMeridian(location));
    }

    static Stream<Arguments> createArguments() {
        return Stream.of(
                arguments(new Location("Gödöllő", 47.59762, 19.37254), false),
                arguments(new Location("Dömsöd", 47.09329, 18.99077), false),
                arguments(new Location("Mór", 47.37117, 18.19426), false),
                arguments(new Location("Pretoria", -25.39891, 27.89274), false),
                arguments(new Location("Sintang", 0, 111.47510), false),
                arguments(new Location("Greenwich", 51.49340, 0), true)
        );
    }

    @DisplayName("ParameterizedCSVFileTest")
    @ParameterizedTest
    @CsvFileSource(resources = "/places.csv")
    void distanceFromTest(String coordinates) {
        String[] temp = coordinates.split(",");
        Location location1 = new Location("Gödöllő", Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
        Location location2 = new Location("Dömsöd", Double.parseDouble(temp[2]), Double.parseDouble(temp[3]));
        double distance = Double.parseDouble(temp[4]);
        Assertions.assertEquals(distance, location1.distanceFrom(location2), 5.0);
    }

    @DisplayName("DynamicTest")
    @TestFactory
    Stream<DynamicTest> equatorDynamicTest() {
        return locations.stream()
                .map(location -> DynamicTest.dynamicTest(location.getName() + " is not on the Equator",
                        () -> Assertions.assertFalse(locationParser.isOnEquator(location))));
    }
}