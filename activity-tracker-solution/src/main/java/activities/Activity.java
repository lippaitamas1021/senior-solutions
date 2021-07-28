package activities;

import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "activities")
@NamedQuery(name = "trackPointsAfterDate", query = "select a from Activity a join fetch a.trackPoints where a.startTime > :startTime")
public class Activity {

    @Id
    @GeneratedValue(generator = "Act_Gen")
    @TableGenerator(name = "Act_Gen", table = "act_id_gen", pkColumnName = "id_gen", valueColumnName = "id_val")
/*  @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "activity_id", nullable = false) */
    private Long id;

    @Column(name = "activity_start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "activity_description", nullable = false, length = 200)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false, length = 20)
    private ActivityType type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "labels", joinColumns = @JoinColumn(name = "activity_id"))
    @Column(name = "label")
    private List<String> labels;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "activity")
    @OrderBy(value = "time")
    private List<TrackPoint> trackPoints;

    @ManyToMany(mappedBy = "activities")
    private List<Area> areas = new ArrayList<>();

    public Activity() {
    }

    public Activity(LocalDateTime startTime, String description, ActivityType type) {
        this.startTime = startTime;
        this.description = description;
        this.type = type;
    }

    public Activity(Long id, LocalDateTime startTime, String description, ActivityType type) {
        this.id = id;
        this.startTime = startTime;
        this.description = description;
        this.type = type;
    }

    @PrePersist
    public void setCreatedAtNow() {
        this.createdAt = LocalDateTime.now();
    }

    @PostPersist
    public void setUpdatedAtNow() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getDescription() {
        return description;
    }

    public ActivityType getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = trackPoints;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public void addTrackPoint(TrackPoint trackPoint) {
        if (trackPoints == null) {
            trackPoints = new ArrayList<>();
        }
        trackPoints.add(trackPoint);
        trackPoint.setActivity(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id) && Objects.equals(startTime, activity.startTime) && Objects.equals(description, activity.description) && type == activity.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, description, type);
    }
}
