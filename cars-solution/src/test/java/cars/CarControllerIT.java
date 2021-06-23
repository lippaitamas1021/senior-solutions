package cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CarControllerIT {

    @Autowired
    CarController carController;

    @Test
    void listCarsTest() {
        List<Car> result =carController.listCars();
        assertThat(result)
                .hasSize(3)
                .extracting(Car::getCarBrand)
                .contains("Opel", "Peugeot", "Ford");
    }

    @Test
    void listTypesTest() {
        List<String> result = carController.listTypes();
        assertThat(result)
                .hasSize(3)
                .contains("Insignia", "308", "Focus");
    }
}
