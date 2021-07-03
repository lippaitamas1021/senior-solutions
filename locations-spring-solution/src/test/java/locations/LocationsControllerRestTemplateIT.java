package locations;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationsControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    LocationsService locationsService;

    @Test
    @RepeatedTest(2)
    void getLocationsTest() {
        locationsService.deleteAllLocations();
        LocationDto locationDto = template.postForObject("/locations",
                new CreateLocationCommand(1, "Poroszló", 48.71649, 19.87614), LocationDto.class);
        assertEquals(1, locationDto.getId());
        template.postForEntity("/locations", new CreateLocationCommand(5, "Pácin", 48.19752, 19.86479), LocationDto.class);
        List<LocationDto> locations = template.exchange("/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {}).getBody();
        assertThat(locations).extracting(LocationDto::getName).contains("Poroszló");
    }
}
