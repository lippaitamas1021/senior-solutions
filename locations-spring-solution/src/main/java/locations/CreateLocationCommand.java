package locations;

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
    private String name;
    @NotBlank(message = "Lon can not be blank")
    private double lat;
    @NotBlank(message = "Lon can not be blank")
    private double lon;
}