package cars;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> cars = new ArrayList<>(List.of(
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

    public List<Car> listCars() {
        return new ArrayList<>(cars);
    }

    public List<String> listTypes() {
        return cars.stream().map(Car::getCarType).distinct().collect(Collectors.toList());
    }

    public void save(Car car) {
        cars.add(car);
    }

    public List<Car> getTheLatestCars() {
        return cars.stream().filter(car -> car.getCarAge() < 5)
                .collect(Collectors.toList());
    }

    public List<Car> searchBrandByNamePart(String namePart) {
        return cars.stream().filter(car -> car.getCarBrand().contains(namePart))
                .collect(Collectors.toList());
    }
}
