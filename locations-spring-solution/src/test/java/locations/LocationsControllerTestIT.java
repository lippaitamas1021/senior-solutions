package locations;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@SpringBootTest
public class LocationsControllerTestIT {

    @Autowired
    LocationsController locationsController;

    @Test
    void getLocationsTest() {
        String result = locationsController.getLocations(Optional.empty()).toString();
        Assertions.assertThat(result).contains("Kemecse");
    }
}
