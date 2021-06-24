package locations;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LocationsControllerIT {

    @Autowired
    LocationsController locationsController;

    @Test
    void getLocationsTest() {
        String result = locationsController.getLocations().toString();
        Assertions.assertThat(result).contains("Kemecse");
    }
}
