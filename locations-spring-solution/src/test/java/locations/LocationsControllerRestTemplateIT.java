//package locations;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.RepeatedTest;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import java.util.List;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class LocationsControllerRestTemplateIT {
//
//    @Autowired
//    TestRestTemplate template;
//
//    @Autowired
//    LocationsService locationsService;
//
//    @BeforeEach
//    void init() {
//        locationsService.deleteAllLocations();
//    }
//
//    @Test
//    @RepeatedTest(2)
//    void getLocationsTest() {
//        LocationDto locationDto = template.postForObject("/locations",
//                new CreateLocationCommand(1, "Poroszló", 48.71649, 19.87614), LocationDto.class);
//        assertEquals(1, locationDto.getId());
//        template.postForEntity("/locations", new CreateLocationCommand(5, "Pácin", 48.19752, 19.86479), LocationDto.class);
//        List<LocationDto> locations = template.exchange("/locations",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<LocationDto>>() {}).getBody();
//        assertThat(locations).extracting(LocationDto::getName).contains("Poroszló");
//    }
//
//    @Test
//    void testFindLocationById() {
//        LocationDto locationDto =
//                template.postForObject("/api/locations", new CreateLocationCommand("Tunyogmatolcs", 47.85617, 19.16739), LocationDto.class);
//        LocationDto expected = template.exchange("/api/locations/1",
//                HttpMethod.GET,
//                null,
//                LocationDto.class)
//                .getBody();
//        assertThat(expected.getName()).isEqualTo("Tunyogmatolcs");
//    }
//
//    @Test
//    void testGetLocationsByNameLatLon() {
//        template.postForObject("/api/locations", new CreateLocationCommand("Anarcs", 47.36715, 19.58931), LocationDto.class);
//        template.postForObject("/api/locations", new CreateLocationCommand("Gemzse", 47.11679, 19.23269), LocationDto.class);
//        template.postForObject("/api/locations", new CreateLocationCommand("Tunyogmatolcs", 47.85617, 19.16739), LocationDto.class);
//        template.postForObject("/api/locations", new CreateLocationCommand("Kisvárda", 47.15793, 19.51800), LocationDto.class);
//        List<LocationDto> expected = template.exchange("/api/locations/minmax?minLat=36&maxLon=20",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<LocationDto>>() {})
//                .getBody();
//        assertThat(expected).hasSize(2);
//        assertThat(expected).extracting(LocationDto::getName)
//                .containsExactly("Anarcs", "Tunyogmatolcs");
//    }
//
//    @Test
//    void testCreateLocation() {
//        LocationDto locationDto =
//                template.postForObject("/api/locations", new CreateLocationCommand("Záhony", 47.81576, 19.78125), LocationDto.class);
//        assertEquals("Záhony", locationDto.getName());
//        assertEquals(47.81576, locationDto.getLat());
//    }
//
//    @Test
//    void testUpdateLocation() {
//        template.postForObject("/api/locations", new CreateLocationCommand("Gemzse", 47.11679, 19.23269), LocationDto.class);
//        template.put("/api/locations/1", new UpdateLocationCommand("Ilk", 47.11679, 19.23269));
//        LocationDto expected = template.exchange("/api/locations/1",
//                HttpMethod.GET,
//                null,
//                LocationDto.class)
//                .getBody();
//        assertEquals("Ilk", expected.getName());
//        assertEquals(47.11679, expected.getLat());
//        assertEquals(19.23269, expected.getLon());
//    }
//
//    @Test
//    void testDeleteLocation() {
//        template.delete("/api/locations/1");
//        List<LocationDto> expected = template.exchange("/api/locations",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<LocationDto>>() {
//                })
//                .getBody();
//        assertThat(expected).hasSize(0);
//    }
//}