package cars;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private String carBrand;
    private String carType;
    private double carAge;
    private CarState carState;
    private List<KmState> kms = new ArrayList<>();

    public Car() {
    }

    public Car(String carBrand, String carType, double carAge, CarState carState, List<KmState> kms) {
        this.carBrand = carBrand;
        this.carType = carType;
        this.carAge = carAge;
        this.carState = carState;
        this.kms = kms;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarType() {
        return carType;
    }

    public double getCarAge() {
        return carAge;
    }

    public CarState getCarState() {
        return carState;
    }

    public List<KmState> getKms() {
        return kms;
    }
}
