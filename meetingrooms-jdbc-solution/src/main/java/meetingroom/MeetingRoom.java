package meetingroom;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@NamedQuery(name = "getAllOrderedByName", query = "select mr.name from MeetingRoom mr order by mr.name")
public class MeetingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int width;

    private int length;

    public MeetingRoom(String name) {
        this.name = name;
    }

    public MeetingRoom(String name, int width, int length) {
        this.name = name;
        this.width = width;
        this.length = length;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", length=" + length +
                '}';
    }
}
