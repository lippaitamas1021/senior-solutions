package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    LocationsService locationsService;

    @BeforeEach
    void init() {
        locationsService = new LocationsService(new ModelMapper());
    }

    @Test
    void getLocationsTest() {
        assertEquals("Biri", locationsService.getLocations(Optional.empty()).get(2).getName());
    }

    @Test
    void getLocationsByIdTest() {
        assertEquals("Kemecse", locationsService.getLocationsById(2).getName());
    }

//    @Test
//    void updateLocation() {
//        Location locationToUpdate = new Location(4, "Pácsony", 47.04196, 16.87554);
//        locationsService.updateLocation(locationToUpdate);
//        assertEquals("Pácsony", locationsService.getLocations().get(3).getName());
//    }

//    @Test
//    void deleteLocation() {
//        assertEquals(4, locationsService.getLocations().size());
//        Location locationToDelete = new Location(6, "Türje", 46.98508, 17.09423);
//        locationsService.addLocation(locationToDelete);
//        assertEquals(5, locationsService.getLocations().size());
//        locationsService.deleteLocation(locationToDelete.getId());
//        assertEquals(4, locationsService.getLocations().size());
//    }
}