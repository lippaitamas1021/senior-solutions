package activities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "activities")
public class Activity {

    @Enumerated(EnumType.STRING)
    @Column(name = "act_type", nullable = false, length = 20)
    private ActivityType activityType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "act_name", length = 200, nullable = false)
    private String name;

    @Column(name = "act_start", nullable = false)
    private LocalDate startTime;

    @Column(name = "act_description", nullable = false, length = 200)
    private String description;

    public Activity() {
    }

    public Activity(String name) {
        this.name = name;
    }

    public Activity(ActivityType activityType, String name, LocalDate startTime) {
        this.activityType = activityType;
        this.name = name;
        this.startTime = startTime;
    }

    public Activity(ActivityType activityType, LocalDate startTime, String description) {
        this.activityType = activityType;
        this.startTime = startTime;
        this.description = description;
    }

    public Activity(ActivityType activityType, Long id, String name, LocalDate startTime, String description) {
        this.activityType = activityType;
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate date) {
        this.startTime = date;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return activityType == activity.activityType && Objects.equals(id, activity.id) && Objects.equals(name, activity.name) && Objects.equals(startTime, activity.startTime) && Objects.equals(description, activity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityType, id, name, startTime, description);
    }
}