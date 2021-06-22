package locations;

import org.assertj.core.api.Condition;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

class LocationServiceTest {

    LocationService locationService = new LocationService();

    @TempDir
    Path tempDir;

    @DisplayName("WritingFile")
    @Test
    void writeLocationsTest() throws IOException {
        Path path = tempDir.resolve("testplaces.csv");

        List<Location> locations = new ArrayList<>();
        locations.add(new Location("Békéscsaba", 0, 72.913752));
        locations.add(new Location("Balmazújváros", 33.197256, 0));
        locations.add(new Location("Baja", 12.557326, 33.416238));
        locations.add(new Location("Óhat-Pusztakócs", 47.62922, 20.93852));
        locations.add(new Location("Beregdaróc", 48.19622, 22.54252));

        locationService.writeLocations(path, locations);
        List<String> lines = Files.readAllLines(path);
        Assertions.assertEquals("Beregdaróc, 48.19622, 22.54252", lines.get(4));
    }

    @DisplayName("ReadingFile")
    @Test
    void readLocationsTest() throws IOException {
        Path path = Path.of("src/main/resources/favlocations.csv");
        List<Location> locations = locationService.readLocations(path);
        Assertions.assertEquals("Óhat-Pusztakócs", locations.get(3).getName());
        Assertions.assertEquals(0, locations.get(0).getLat());
        Assertions.assertEquals(0, locations.get(1).getLon());
    }

    @DisplayName("ReadingFileByHamcrestMatchers")
    @Test
    void readLocationsByHamcrestTest() throws IOException {
        Path path = Path.of("src/main/resources/favlocations.csv");
        List<Location> locations = locationService.readLocations(path);
        MatcherAssert.assertThat(locations.get(1).getName(), equalTo("Balmazújváros"));
        MatcherAssert.assertThat(locations.size(), equalTo(5));
    }

    @DisplayName("ZeroCoordinateHamcrest")
    @Test
    void ownHamcrestMatcherTest() {
        Path path = Path.of("src/main/resources/favlocations.csv");
        MatcherAssert.assertThat(locationService.readLocations(path).get(2),
                LocationWithZeroCoordinate.zeroCoordinate(equalTo(false)));
    }

    @DisplayName("ReadingFileByAssertJMatchers")
    @Test
    void readLocationsByAssertJTest() throws IOException {
        Path path = Path.of("src/main/resources/favlocations.csv");
        List<Location> locations = locationService.readLocations(path);
        org.assertj.core.api.Assertions.assertThat(locations.get(2).getName().equals("Baja"));
        org.assertj.core.api.Assertions.assertThat(locations).hasSize(5);
        org.assertj.core.api.Assertions.assertThat(locations.get(4).getLat()).isEqualTo(48.19622);
    }

    @DisplayName("CoordinateZeroByAssertJ")
    @Test
    void ownAssertJassertTest() {
        Path path = Path.of("src/main/resources/favlocations.csv");
        Condition<Location> zeroCoordinate = new Condition<>(l -> l.getLat() != 0.0, "The coordinate does not equals to 0");
        org.assertj.core.api.Assertions.assertThat(locationService.readLocations(path).get(1)).has(zeroCoordinate);
    }

    @DisplayName("LocationInTheList")
    @Test
    void calculateDistanceTest() {
        LocationRepository locationRepository = new LocationRepository();
        locationRepository.addLocation(new Location("Gödöllő", 47.59762, 19.37254));
        locationRepository.addLocation(new Location("Dömsöd", 47.09329, 18.99077));
        locationRepository.addLocation(new Location("Mór", 47.37117, 18.19426));
        locationRepository.addLocation(new Location("Pretoria", -25.39891, 27.89274));
        locationRepository.addLocation(new Location("Sintang", 0,111.47510));

        DistanceService distanceService = new DistanceService(locationRepository);
        Optional<Double> distance = distanceService.calculateDistance("Gödöllő", "Pretoria");
        Optional<Double> wrongDistance = distanceService.calculateDistance("Sintang", "Beregdaróc");

        Assertions.assertEquals(8161, distance.get(), 5.0);
        Assertions.assertTrue(wrongDistance.equals(Optional.empty()));
    }

    @DisplayName("MockingRepo")
    @Test
    void testCalculateDistanceMock() {
        LocationRepository repository = Mockito.mock(LocationRepository.class);
        DistanceService distanceService = new DistanceService(repository);
        Optional<Double> distance1 = distanceService.calculateDistance("Sintang", "Békéscsaba");
        Assertions.assertTrue(distance1.equals(Optional.empty()));
    }
}