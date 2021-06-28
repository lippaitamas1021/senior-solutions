package bikesharing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BikeControllerTest {

    @Mock
    BikeService bikeService;

    @InjectMocks
    BikeController bikeController;

    @Test
    void listSharesTest() {
        when(bikeService.getAllShares()).thenReturn(List.of(new BikeShareDto("Trek01","Liptom21", LocalDateTime.of(
                2021, 6, 27, 10, 15,10,25),36.6)));
        List<BikeShareDto> result = bikeController.listShares();
        assertThat(result)
                .extracting(BikeShareDto::getBikeId)
                .containsOnly("Trek01");
        verify(bikeService).getAllShares();
    }

    @Test
    void listUsersTest() {
        when(bikeService.getUserIds()).thenReturn(List.of("LipTom21"));
        List<String> result = bikeController.listUsers();
        assertThat(result)
                .containsOnly("LipTom21");
        verify(bikeService).getUserIds();
    }
}