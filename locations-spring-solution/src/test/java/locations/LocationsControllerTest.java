package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void getLocationsTest() {
        when(locationsService.getLocations(Optional.empty())).thenReturn(new ArrayList<>(List.of(
                new LocationDto("Tarpa", 47.85617, 19.257301),
                new LocationDto("Panyola", 47.16497, 19.87642),
                new LocationDto("Jánd", 47.56701, 19.91465)
        )));
        assertAll(
                () -> assertThat(locationsController.getLocations(Optional.empty()).toString().contains("Tarpa")),
                () -> assertEquals(3, locationsController.getLocations(Optional.empty()).size())
        );
    }
}