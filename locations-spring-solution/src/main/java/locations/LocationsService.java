package locations;

import org.modelmapper.ModelMapper;

import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private AtomicLong idGen = new AtomicLong();

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private List<Location> locations = Collections.synchronizedList(new ArrayList<>(
            List.of(
                    new Location(1, "Bükkszentkereszt", 48.06673, 20.63469),
                    new Location(2, "Kemecse", 48.06762, 21.79969),
                    new Location(3, "Biri", 47.81238, 21.85451),
                    new Location(4, "Kiskunhalas", 46.42900, 19.48831)
            ))
    );

    public List<LocationDto> getLocations(Optional<String> prefix) {
        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
        List<Location> filteredLocations = locations.stream()
                .filter(location -> prefix.isEmpty() || location.getName().startsWith(prefix.get()))
                .collect(Collectors.toList());
        return modelMapper.map(filteredLocations, targetListType);
    }

    public LocationDto getLocationsById(long id) {
        return modelMapper.map(locations.stream()
                        .filter(location -> location.getId() == id)
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("Location with id: " + id + " not found.")),
                LocationDto.class);
    }

    public List<LocationDto> getLocationsByNameLatLon(Optional<String> prefix,
                                                      Optional<Double> minLat,
                                                      Optional<Double> maxLat,
                                                      Optional<Double> minLon,
                                                      Optional<Double> maxLon) {
        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
        List<Location> filteredLocations = locations.stream()
                .filter(location -> prefix.isEmpty() || location.getName().startsWith(prefix.get()))
                .filter(location -> minLat.isEmpty() || location.getLat() >= minLat.get())
                .filter(location -> maxLat.isEmpty() || location.getLat() <= maxLat.get())
                .filter(location -> minLon.isEmpty() || location.getLon() >= minLon.get())
                .filter(location -> maxLon.isEmpty() || location.getLon() <= maxLon.get())
                .collect(Collectors.toList());
        return modelMapper.map(filteredLocations, targetListType);
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(idGen.incrementAndGet(), command.getName(), command.getLat(), command.getLon());
        locations.add(location);
        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location location = locations.stream()
                .filter(loc -> loc.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("Location with id: " + id + " not found."));
        location.setName(command.getName());
        location.setLat(command.getLat());
        location.setLon(command.getLon());
        return modelMapper.map(location, LocationDto.class);
    }

    public void deleteLocation(long id) {
        Location location = locations.stream()
                .filter(loc -> loc.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Location with id: " + id + " not found."));
        locations.remove(location);
    }

    public void deleteAllLocations() {
        idGen = new AtomicLong();
        locations.clear();
    }
}
