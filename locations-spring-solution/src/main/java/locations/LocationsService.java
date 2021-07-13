package locations;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class LocationsService {

    private JdbcTemplate jdbcTemplate;
    private ModelMapper modelMapper;
    private LocationsDAO locationsDAO = new LocationsDAO(jdbcTemplate);

    @Value("${locations.name-auto-uppercase}")
    private boolean nameAutoUpperCase;

    public LocationsService(ModelMapper modelMapper, LocationsDAO locationsDAO) {
        this.modelMapper = modelMapper;
        this.locationsDAO = locationsDAO;
    }

    public LocationsService(LocationsDAO locationsDAO) {
        this.locationsDAO = locationsDAO;
    }

    public List<LocationDto> getLocations(Optional<String> prefix) {
       return locationsDAO.findAll().stream()
               .map(l -> modelMapper.map(l, LocationDto.class))
               .collect(Collectors.toList());
    }

    public LocationDto getLocationById(long id) {
        return modelMapper.map(locationsDAO.findById(id),LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(command.getName());
        locationsDAO.createLocation(location);
        log.info("Location with id = " + location.getId() + " created");
        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location locationToUpdate = new Location(id, command.getName());
        locationsDAO.updateLocation(locationToUpdate);
        log.info("Location with id = " + locationToUpdate.getId() + " updated");
        return modelMapper.map(locationToUpdate, LocationDto.class);
    }

    public void deleteLocation(long id) {
        locationsDAO.deleteById(id);
        log.info("Location with id = " + id + " deleted");
    }

    public void deleteAllLocations() {
        locationsDAO.deleteAll();
    }
}
