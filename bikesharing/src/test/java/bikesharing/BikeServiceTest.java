package bikesharing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class BikeServiceTest {

    BikeService bikeService;

    @BeforeEach
    void init(){
        bikeService = new BikeService(new ModelMapper());
    }

    @Test
    void listSharesTest() {
        List<BikeShareDto> result = bikeService.getAllShares();
        assertThat(result)
                .extracting(BikeShareDto::getBikeId)
                .contains("FH676");
    }

    @Test
    void listUsersTest() {
        List<String> result = bikeService.getUserIds();
        assertThat(result)
                .hasSize(5)
                .contains("US3434");
    }
}
