package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    LocationsService locationsService;

    @BeforeEach
    void init() {
        locationsService = new LocationsService();
    }


    @Test
    void addLocationTest() {
        assertEquals(4, locationsService.getLocations().size());
        locationsService.addLocation(new Location(5, "Zalaszentgrót", 46.94655, 17.08084));
        assertEquals(5, locationsService.getLocations().size());
    }

    @Test
    void creatLocationTest() {
        locationsService.creatLocation(6, "Oszkó", 47.04196, 16.87554);
        assertEquals(5, locationsService.getLocations().size());
    }

    @Test
    void getLocationsTest() {
        assertEquals("Biri", locationsService.getLocations().get(2).getName());
    }

    @Test
    void getLocationsByIdTest() {
        assertEquals("Kemecse", locationsService.getLocationsById(2).get(0).getName());
    }

    @Test
    void getLocationsByNamePrefix() {
        assertEquals(1, locationsService.getLocationsByNamePrefix("Bükk").size());
    }

    @Test
    void updateLocation() {
        Location locationToUpdate = new Location(4, "Pácsony", 47.04196, 16.87554);
        locationsService.updateLocation(locationToUpdate);
        assertEquals("Pácsony", locationsService.getLocations().get(3).getName());
    }

    @Test
    void deleteLocation() {
        assertEquals(4, locationsService.getLocations().size());
        Location locationToDelete = new Location(6, "Türje", 46.98508, 17.09423);
        locationsService.addLocation(locationToDelete);
        assertEquals(5, locationsService.getLocations().size());
        locationsService.deleteLocation(locationToDelete.getId());
        assertEquals(4, locationsService.getLocations().size());
    }
}