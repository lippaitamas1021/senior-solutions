package cars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    CarService carService;

    @InjectMocks
    CarController carController;

    @Test
    void listCarsTest() {
        List<Car> cars = new ArrayList<>(List.of(
                new Car("Opel", "Insignia", 2.5, CarState.EXCELLENT,
                        new ArrayList<>(List.of
                                (new KmState(LocalDate.of(2020,11,1), 45000),
                                        (new KmState(LocalDate.of(2021,6,1), 79000))))),
                new Car("Peugeot", "308", 12, CarState.NORMAL,
                        new ArrayList<>(List.of
                                (new KmState(LocalDate.of(2012,6,30), 112000),
                                        (new KmState(LocalDate.of(2016,5,31), 201000))))),
                new Car("Ford", "Focus", 6, CarState.POOR_CONDITION,
                        new ArrayList<>(List.of
                                (new KmState(LocalDate.of(2017,12,31), 46500),
                                        (new KmState(LocalDate.of(2020,1,1), 122000)))))));

        when(carService.listCars()).thenReturn(cars);

        List<Car> result =carController.listCars();
        assertThat(result)
                .hasSize(3)
                .extracting(Car::getCarBrand)
                .contains("Opel", "Peugeot", "Ford");
        verify(carService, times(1)).listCars();
    }

    @Test
    void listTypes() {
        when(carService.listTypes()).thenReturn(List.of("Insignia", "308", "Focus"));
        List<String> result = carController.listTypes();
        assertThat(result)
                .hasSize(3)
                .contains("Insignia", "308", "Focus");
        verify(carService, times(1)).listTypes();
    }
}