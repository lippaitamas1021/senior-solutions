package locations;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class HelloLocationTest {

    @Test
    void sayHello() {
        HelloProperties helloProperties = new HelloProperties();
        helloProperties.setMessage("Hello");
        HelloLocation helloLocation = new HelloLocation(helloProperties);
        String message = helloLocation.sayHello();
        assertThat(message).startsWith("Hello");
    }
}