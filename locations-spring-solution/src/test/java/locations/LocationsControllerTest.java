package locations;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void getLocationsTest() {
        when(locationsService.getLocations(Optional.empty())).thenReturn(List.of(
                new LocationDto(11, "Záhony", 47.89745, 18.25697),
                new LocationDto(12, "Óhat-Pusztakócs", 47.15678, 18.36745)));
        String result = locationsController.getLocations(Optional.empty()).toString();
        Assertions.assertThat(result).contains("Záhony");
    }
}