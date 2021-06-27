package bikesharing;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BikeShare {

    private String bikeId;
    private String userId;
    private LocalDateTime dateTime;
    private double distance;
}
