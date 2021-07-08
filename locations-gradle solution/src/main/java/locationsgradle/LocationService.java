package locationsgradle;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private List<Location> locations = new ArrayList(List.of(
            new Location(1, "Kisar", 47.99999, 17.88888),
            new Location(2, "Nagyar", 47.88888, 17.99999)
    )
    );

    public List<String> getLocations() {
        return locations.stream()
                .map(Location::getName)
                .collect(Collectors.toList());
    }
}
