package meetingrooms;

import java.util.Objects;

public class MeetingRoom {

    private int id;
    private String name;
    private double width;
    private double length;
    private double area;

    public MeetingRoom(int id, String name, double length, double width, double area) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.area = width * length;
    }

    public MeetingRoom(String name, double width, double length) {
        this.id = id++;
        this.name = name;
        this.width = width;
        this.length = length;
        this.area = length * width;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double countArea() {
        return length * width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingRoom that = (MeetingRoom) o;
        return Double.compare(that.area, area) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(area);
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", length=" + length +
                ", area=" + countArea() +
                '}';
    }
}
