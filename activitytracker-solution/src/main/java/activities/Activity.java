package activities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

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

    public Activity() {
    }

    public Activity(String name) {
        this.name = name;
    }

    public Activity(String name, ActivityType activityType, LocalDate date) {
        this.name = name;
        this.activityType = activityType;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
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