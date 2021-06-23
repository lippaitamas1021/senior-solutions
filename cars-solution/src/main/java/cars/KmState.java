package cars;

import java.time.LocalDate;


public class KmState {

    private LocalDate date;
    private double actualKm;

    public KmState(LocalDate date, double actualKm) {
        this.date = date;
        this.actualKm = actualKm;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getActualKm() {
        return actualKm;
    }
}
