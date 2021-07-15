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
public class Activity {

    @Enumerated(EnumType.STRING)
    private ActivityType activityType = ActivityType.DYNAMIC;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}