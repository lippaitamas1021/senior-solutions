//package locations;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import java.util.ArrayList;
//import java.util.List;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@WebMvcTest(LocationsController.class)
//public class LocationControllerWebMvcIT {
//
//    @MockBean
//    LocationsService locationsService;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void getLocationsTest() throws Exception {
//        when(locationsService.getLocations(any())).thenReturn(new ArrayList<>(List.of(
//                new LocationDto(1, "Tarpa", 47.85617, 19.257301),
//                new LocationDto(2, "Panyola", 47.16497, 19.87642),
//                new LocationDto(3, "Jánd", 47.56701, 19.91465)
//        )));
//        mockMvc.perform(get("/locations")).andDo(print());
//    }
//}
