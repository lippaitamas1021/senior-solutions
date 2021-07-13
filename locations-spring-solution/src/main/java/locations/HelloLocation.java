package locations;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@EnableConfigurationProperties(HelloProperties.class)
public class HelloLocation {

    private HelloProperties helloProperties;

    public String sayHello() {
        return helloProperties.getMessage() + " " + LocalDateTime.now();
    }
}
