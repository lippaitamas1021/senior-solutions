package bikesharing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BikeShareDto {

    private String bikeId;
    private String userId;
    private LocalDateTime dateTime;
    private double distance;
}
