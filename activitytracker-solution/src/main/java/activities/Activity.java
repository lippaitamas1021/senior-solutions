package activities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activities")
@IdClass(ActivityId.class)
public class Activity {

    @Enumerated(EnumType.STRING)
    private ActivityType activityType = ActivityType.DYNAMIC;

    @Id
    private String depName;

    @Id
    private long id;

    @Column(name = "act_name", length = 20, nullable = false)
    private String name;

    private LocalDate date;

    public Activity(String name) {
        this.name = name;
    }

    public Activity(String name, ActivityType activityType, LocalDate date) {
        this.name = name;
        this.activityType = activityType;
        this.date = date;
    }

    public Activity(String depName, long id, String name) {
        this.depName = depName;
        this.id = id;
        this.name = name;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}