package bikesharing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class BikeControllerTestIT {

    @Autowired
    BikeController bikeController;

    @Test
    void listSharesTest() {
        List<BikeShareDto> result = bikeController.listShares();
        assertThat(bikeController.listShares())
                .hasSize(1)
                .extracting("Trek01")
                .containsExactly("Trek01");
    }

    @Test
    void listUsersTest() {
        String userId = bikeController.listUsers().get(1);
        assertThat(userId).contains("LipTom21");
    }
}
