package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LocationsController {

    LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping("/locations")
    public List<String> getLocations() {
        return locationsService.getLocations().stream().map(Location::getName).collect(Collectors.toList());
//        return locations.stream().map(Location::getName).collect(Collectors.toList());
    }

    @GetMapping("/id")
    public List<Location> getLocationsById(long id) {
        return locationsService.getLocationsById(id);
    }

    @GetMapping("/prefix")
    public List<Location> getLocationsByNamePrefix(String prefix) {
        return locationsService.getLocationsByNamePrefix(prefix);
    }
}
