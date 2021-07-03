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
    private List<LocationDto> favLocations = Collections.synchronizedList(new ArrayList<>(List.of(
            new LocationDto(1, "Tarpa", 47.85617, 19.257301),
            new LocationDto(2, "Panyola", 47.16497, 19.87642),
            new LocationDto(3, "Jánd", 47.56701, 19.91465)
    )));
    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        idGen.getAndAdd(3);
    }

    public List<LocationDto> getLocations(Optional<String> prefix) {
        Type targetListType =
                new TypeToken<List<LocationDto>>(){}.getType();
        List<LocationDto> filtered = favLocations.stream()
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
        Location location = new Location(idGen.incrementAndGet(), command.getName(), command.getLat(), command.getLon());
        favLocations.add(modelMapper.map(location, LocationDto.class));
        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        LocationDto locationToUpdate = favLocations.stream()
                .filter(locationDto -> locationDto.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Location with id " + id + "not found"));
        locationToUpdate.setName(command.getName());
        locationToUpdate.setLat(command.getLat());
        locationToUpdate.setLon(command.getLon());
        return modelMapper.map(locationToUpdate, LocationDto.class);
    }

    public void deleteLocation(long id) {
        LocationDto locationToDelete = favLocations.stream()
                .filter(locationDto -> locationDto.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Location with id " + id + "not found"));
        favLocations.remove(locationToDelete);
    }

    public void deleteAllLocations() {
        idGen = new AtomicLong();
        favLocations.clear();
    }
}
