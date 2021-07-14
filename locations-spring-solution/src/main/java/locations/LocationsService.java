package locations;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class LocationsService {

    private ModelMapper modelMapper = new ModelMapper();

    private LocationsRepository locationsRepository;

    @Autowired
    public LocationsService(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    @Value("${locations.name-auto-uppercase}")
    private boolean nameAutoUpperCase;

    public List<LocationDto> getLocations(Optional<String> prefix) {
       return locationsRepository.findAll().stream()
               .map(l -> modelMapper.map(l, LocationDto.class))
               .collect(Collectors.toList());
    }

    public LocationDto getLocationById(long id) {
        return modelMapper.map(locationsRepository.findById(id)
                .orElseThrow(LocationNotFoundException::new),LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(command.getName());
        locationsRepository.save(location);
        log.info("Location with id = " + location.getId() + " created");
        log.debug("Location under the name {} created", command.getName());
        return modelMapper.map(location, LocationDto.class);
    }

    @Transactional
    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location locationToUpdate =locationsRepository.findById(id)
                .orElseThrow(LocationNotFoundException::new);
        locationToUpdate.setName(command.getName());
        log.info("Location with id = " + locationToUpdate.getId() + " updated");
        return modelMapper.map(locationToUpdate, LocationDto.class);
    }

    public void deleteLocation(long id) {
        locationsRepository.deleteById(id);
        log.info("Location with id = " + id + " deleted");
    }

    public void deleteAllLocations() {
        locationsRepository.deleteAll();
    }
}
