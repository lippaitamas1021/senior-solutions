package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.startsWith;

class LocationServiceTest {

    List<Location> locations = new ArrayList<>();

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        LocationService locationService = new LocationService();
        locations = Arrays.asList(
                new Location("Gödöllő", 47.59762, 19.37254),
                new Location("Dömsöd", 47.09329, 18.99077),
                new Location("Mór", 47.37117, 18.19426),
                new Location("Pretoria", -25.39891, 27.89274)
        );
    }

    @Test
    @DisplayName("ReadLocations By Hamcrest")
    void readLocations() {
        Path file = tempDir.resolve("locations.csv");
        LocationService locationService = new LocationService();
        locationService.writeLocations(file, locations);
        List<Location> actual = locationService.readLocations(file);

        assertThat(actual.get(0), allOf(
                hasProperty("name",equalTo("Gödöllő")),
                hasProperty("lat",equalTo(47.59762)),
                hasProperty("lon",equalTo(19.37254))
                )
        );
        assertThat(actual.get(3), allOf(
                hasProperty("name",equalTo("Pretoria")),
                hasProperty("lat",equalTo(-25.39891)),
                hasProperty("lon",equalTo(27.89274))
                )
        );

        assertThat(locations,equalTo(actual));

        assertThat(actual.get(0),instanceOf(Location.class));

        assertThat(actual,hasItem(hasProperty("name",startsWith("Döm"))));
    }
}