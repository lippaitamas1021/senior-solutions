package cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    CarService carService;

    @BeforeEach
    void init() {
        carService = new CarService();
}

    @Test
    void listCarsTest() {
        List<Car> result = carService.listCars();
        assertEquals(3, result.size());
        assertThat(result)
                .hasSize(3)
                .extracting(Car::getCarBrand)
                .contains("Opel", "Peugeot", "Ford");
    }

    @Test
    void listTypesTest() {
        List<String> result = carService.listTypes();
        assertTrue(carService.listTypes().contains("Focus"));
        assertThat(result)
                .hasSize(3)
                .contains("Insignia", "308", "Focus");
    }

    @Test
    void saveTest() {
        carService.save(new Car("Aston Martin", "DB9", 1, CarState.EXCELLENT,
                new ArrayList<>(List.of
                        (new KmState(LocalDate.of(2020,12,31), 25000),
                                new KmState(LocalDate.of(2021,6,1), 52000)
                        )
                )));
        assertEquals(4, carService.listCars().size());
    }

    @Test
    void getTheLatestCarsTest() {
        assertEquals(1, carService.getTheLatestCars().size());
    }

    @Test
    void searchBrandByNamePartTest() {
        assertEquals(1, carService.searchBrandByNamePart("eug").size());
    }
}