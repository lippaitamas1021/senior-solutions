package locations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;

@Data
@ConfigurationProperties(prefix = "locations")
@Validated
public class HelloProperties {

    @NotBlank
    private String message;
}