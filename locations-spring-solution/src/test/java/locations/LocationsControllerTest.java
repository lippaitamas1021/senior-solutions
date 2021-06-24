package locations;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void getLocationsTest() {
        when(locationsService.getLocations()).thenReturn(Arrays.asList(
                new Location("Óhat-Pusztakócs"), new Location("Záhony")
        ));
        String result = locationsController.getLocations().toString();
        Assertions.assertThat(result).contains("Záhony");
    }

    @Test
    void getLocationsByNamePrefixTest() {
        when(locationsService.getLocationsByNamePrefix("vár")).thenReturn(Collections.singletonList(new Location("Kisvárda")));
        assertEquals(1, locationsService.getLocationsByNamePrefix("vár").size());
    }
}