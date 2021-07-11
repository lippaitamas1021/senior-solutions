package locations;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LocationsService {

    private ModelMapper modelMapper;
    private AtomicLong idGen = new AtomicLong();

    @Value("${locations.name-auto-uppercase}")
    private boolean nameAutoUpperCase;

    private List<Location> favLocations = Collections.synchronizedList(new ArrayList<>(
            Arrays.asList(
                    new Location(idGen.incrementAndGet(), "Tarpa", 47.85617, 19.257301),
                    new Location(idGen.incrementAndGet(), "Panyola", 47.16497, 19.87642),
                    new Location(idGen.incrementAndGet(), "Jánd", 47.56701, 19.91465)
            )));

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations(Optional<String> prefix) {
        Type targetListType =
                new TypeToken<List<LocationDto>>(){}.getType();
        List<Location> filtered = favLocations.stream()
                .filter(loc -> prefix.isEmpty() || loc.getName().startsWith(prefix.get()))
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);
    }

    public LocationDto getLocationById(long id) {
        return modelMapper.map(favLocations.stream()
                        .filter(locationDto -> locationDto.getId() == id)
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("Location with id " + id + " not found")),
                LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(idGen.incrementAndGet(), command.getName(),  command.getLat(), command.getLon());
        favLocations.add(location);
        log.info("Location with id = " + location.getId() + " created");
        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location locationToUpdate = favLocations.stream()
                .filter(locationDto -> locationDto.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("Location with id " + id + "not found"));
        locationToUpdate.setName(command.getName());
        locationToUpdate.setLat(command.getLat());
        locationToUpdate.setLon(command.getLon());
        log.info("Location with id = " + locationToUpdate.getId() + " updated");
        return modelMapper.map(locationToUpdate, LocationDto.class);
    }

    public void deleteLocation(long id) {
        Location locationToDelete = favLocations.stream()
                .filter(locationDto -> locationDto.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Location with id " + id + "not found"));
        favLocations.remove(locationToDelete);
        log.info("Location with id = " + locationToDelete.getId() + " deleted");
    }

    public void deleteAllLocations() {
        idGen = new AtomicLong();
        favLocations.clear();
    }
}
