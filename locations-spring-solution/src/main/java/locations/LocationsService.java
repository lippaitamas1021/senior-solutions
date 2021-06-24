package locations;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private final List<Location> locations = new ArrayList<>(
            List.of(
                    new Location(1, "Bükkszentkereszt", 48.06673, 20.63469),
                    new Location(2, "Kemecse", 48.06762, 21.79969),
                    new Location(3, "Biri", 47.81238, 21.85451),
                    new Location(4, "Kiskunhalas", 46.42900, 19.48831)
            )
    );

    public void addLocation(Location location) {
        locations.add(location);
    }

    public void creatLocation(long id, String name, double lat, double lon) {
        locations.add(new Location(id, name, lat, lon));
    }

    public List<Location> getLocations() {
        return new ArrayList<>(locations);
    }

    public List<Location> getLocationsById(long id) {
        return locations.stream()
                .filter(location -> location.getId() == id)
                .collect(Collectors.toList());
    }

    public List<Location> getLocationsByNamePrefix(String prefix) {
        return locations.stream()
                .filter(location -> location.getName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    public void updateLocation(Location location) {
        Location updated = locations.stream().filter(location1 -> location1.getId() == location.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The given location not found"));
                updated.setId(location.getId());
                updated.setName(location.getName());
                updated.setLat(location.getLat());
                updated.setLon(location.getLon());
    }

    public void deleteLocation(long id) {
        Location result = locations.stream()
                .filter(location1 -> location1.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The given location not found"));
        locations.remove(result);
    }
}
