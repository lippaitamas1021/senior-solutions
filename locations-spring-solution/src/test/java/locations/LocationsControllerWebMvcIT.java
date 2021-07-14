//package locations;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import static org.hamcrest.Matchers.equalTo;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//import static org.mockito.ArgumentMatchers.any;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import java.util.List;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(controllers = LocationsController.class)
//public class LocationsControllerWebMvcIT {
//
//    @MockBean
//    LocationsService service;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Test
//    void testGetLocations() throws Exception {
//        when(service.getLocations(any())).thenReturn(List.of(new LocationDto(1L, "Gemzse", 47.11679, 19.23269),
//                new LocationDto(2L, "Ilk", 47.11679, 19.23269)));
//        mockMvc.perform(get("/api/locations"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name", equalTo("Gemzse")));
//    }
//
//    @Test
//    void testFindLocationById() throws Exception {
//        when(service.getLocationById(anyLong())).thenReturn(new LocationDto(3L, "Záhony", 47.81576, 19.78125));
//        mockMvc.perform(get("/api/locations/3"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", equalTo("Záhony")));
//    }
//
//    @Test
//    void testCreateLocation() throws Exception {
//        when(service.createLocation(any())).thenReturn(new LocationDto(4L, "Anarcs", 47.36715, 19.58931));
//        mockMvc.perform(post("/api/locations").contentType(APPLICATION_JSON)
//                .content("{\n" +
//                        "  \"name\": \"Anarcs\",\n" +
//                        "  \"lat\": 47.36715,\n" +
//                        "  \"lon\": 19.58931\n" +
//                        "}"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", equalTo("Anarcs")));
//    }
//
//    @Test
//    void testCreateLocationTwo() throws Exception {
//        when(service.createLocation(any())).thenReturn(new LocationDto(5L, "Tunyogmatolcs", 47.85617, 19.16739));
//        CreateLocationCommand command = new CreateLocationCommand("Kisvárda", 47.15793, 19.51800);
//        String json = objectMapper.writeValueAsString(command);
//        mockMvc.perform(post("/api/locations").contentType(APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", equalTo("Tunyogmatolcs")));
//    }
//
//    @Test
//    void testUpdateLocation() throws Exception {
//        when(service.updateLocation(anyLong(), any())).thenReturn(new LocationDto(6L, "Pácin", 48.19752, 19.86479));
//        mockMvc.perform(put("/api/locations/6").contentType(APPLICATION_JSON)
//                .content("{\n" +
//                        "  \"name\": \"Anarcs\",\n" +
//                        "  \"lat\": 47.36715,\n" +
//                        "  \"lon\": 19.58931\n" +
//                        "}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", equalTo("Pácin")));
//    }
//
//    @Test
//    void testUpdateLocationTwo() throws Exception {
//        when(service.updateLocation(anyLong(), any())).thenReturn(new LocationDto(5L, "Poroszló", 48.71649, 19.87614));
//        CreateLocationCommand command = new CreateLocationCommand("Gemzse", 47.11679, 19.23269);
//        String json = objectMapper.writeValueAsString(command);
//        mockMvc.perform(put("/api/locations/5").contentType(APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", equalTo("Poroszló")));
//    }
//}
