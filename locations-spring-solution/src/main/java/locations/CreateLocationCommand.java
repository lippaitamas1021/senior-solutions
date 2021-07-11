package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationCommand {

    private long id;

    @NotBlank(message = "Name can not be blank")
    @Schema(description = "Name of location: ", example = "Szabolcsbáka")
    private String name;

    @Coordinate
    @Schema(description = "Latitude of the location ")
    private double lat;

    @Coordinate
    @Schema(description = "Longitude of the location ")
    private double lon;

    public CreateLocationCommand(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }
}