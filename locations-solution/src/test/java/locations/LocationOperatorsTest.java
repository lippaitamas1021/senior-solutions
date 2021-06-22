package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@LocationOperationsFeatureTest
class LocationOperatorsTest {

    private LocationOperators locationOperators = new LocationOperators();
    private List<Location> locations = new ArrayList<>();

    @BeforeEach
    void init() {
        locationOperators = new LocationOperators();
        locations = Arrays.asList(
                new Location("Gödöllő", 47.59762, 19.37254),
                new Location("Dömsöd", 47.09329, 18.99077),
                new Location("Mór", 47.37117, 18.19426),
                new Location("Pretoria", -25.39891, 27.89274)
        );
    }

    @Test
    void filterOnNorth() {
        locations = locationOperators.filterOnNorth(locations);
        assertAll(
                () -> assertEquals(4, locations.size()),
                () -> assertEquals("Mór", locations.get(2).getName()),
                () -> assertEquals(-25.39891, locations.get(3).getLat())
        );
    }
}