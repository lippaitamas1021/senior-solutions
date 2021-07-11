package locations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    LocationsService locationsService = new LocationsService(new ModelMapper());

    @DisplayName("getLocations")
    @Test
    void getLocationsTest() {
        assertEquals("Tarpa", locationsService.getLocations(Optional.empty()).get(0).getName());
    }

    @DisplayName("getLocationById")
    @Test
    void getLocationByIdTest() {
        assertEquals("Panyola", locationsService.getLocationById(2).getName());
    }

    @DisplayName("updateLocation")
    @Test
    void updateLocationTest() {
        UpdateLocationCommand locationToUpdate = new UpdateLocationCommand("Múcsony", 47.99999, 18.88888);
        assertEquals("Múcsony", locationsService.updateLocation(3, locationToUpdate).getName());
    }

    @DisplayName("deleteLocation")
    @Test
    void deleteLocation() {
        Assertions.assertEquals(3, locationsService.getLocations(Optional.empty()).size());
        CreateLocationCommand locationToDelete = new CreateLocationCommand(1, "Türje", 46.98508, 17.09423);
        locationsService.createLocation(locationToDelete);
        Assertions.assertEquals(4, locationsService.getLocations(Optional.empty()).size());
        locationsService.deleteLocation(locationToDelete.getId());
        Assertions.assertEquals(3, locationsService.getLocations(Optional.empty()).size());
    }

    @DisplayName("createLocationWithWrongParams")
    @Test
    void createLocationWithWrongParams() {
        CreateLocationCommand command = new CreateLocationCommand(21, "Tyukod", -91.0000, -181.00000);
        LocationDto result = locationsService.createLocation(command);
        System.out.println(result);
    }
}