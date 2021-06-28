package bikesharing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BikeControllerTestIT {

    @Autowired
    BikeController bikeController;

    @Test
    void listSharesTest() {
        List<BikeShareDto> result = bikeController.listShares();
        assertThat(bikeController.listShares())
                .hasSize(5)
                .extracting(BikeShareDto::getUserId)
                .contains("US346");
    }

    @Test
    void listUsersTest() {
        String userId = bikeController.listUsers().get(1);
        assertThat(userId).contains("US3a34");
    }
}
