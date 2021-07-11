package locations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/locations", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Tag(name = "Locations MicroService")
public class LocationsController {

    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping
    @Operation(summary = "List of all locations")
    @ApiResponse(responseCode = "200", description = "Successful query")
    public List<LocationDto> getLocations(@RequestParam Optional<String> prefix) {
        return locationsService.getLocations(prefix);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Location found by id")
    @ApiResponse(responseCode = "200", description = "Location found")
    @ApiResponse(responseCode = "404", description = "Location not found")
    public LocationDto getLocationById(@PathVariable("id") long id) {
            return locationsService.getLocationById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a location")
    @ApiResponse(responseCode = "201", description = "Creation completed")
    public LocationDto createLocation(@Valid @RequestBody CreateLocationCommand command) {
        return locationsService.createLocation(command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update location")
    @ApiResponse(responseCode = "200", description = "Update completed")
    public LocationDto updateLocation(@PathVariable("id") long id, @Valid @RequestBody UpdateLocationCommand command) {
        return locationsService.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete location")
    @ApiResponse(responseCode = "204", description = "Deletion completed")
    public void deleteLocation(@PathVariable("id") long id) {
        locationsService.deleteLocation(id);
    }
}
