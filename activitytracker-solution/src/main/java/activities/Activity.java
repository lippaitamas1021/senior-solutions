package activities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDateTime startTime;

    @Column(name = "act_description", nullable = false, length = 200)
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "nicknames", joinColumns = @JoinColumn(name = "act_id"))
    @Column(name = "nickname")
    private Set<String> nickNames;

    @ElementCollection
    @CollectionTable(name = "bookings", joinColumns = @JoinColumn(name = "act_id"))
    @AttributeOverride(name = "startDate", column = @Column(name = "start_date"))
//    @AttributeOverride(name = "daysTaken", column = @Column(name = "days"))
    private Set<ActivityBookings> activityBookings;

    @ElementCollection
    @CollectionTable(name = "labels", joinColumns = @JoinColumn(name = "activity_id"))
    @Column(name = "label")
    private List<String> labels = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "phone_numbers", joinColumns = @JoinColumn(name = "act_id"))
    @MapKeyColumn(name = "phone_type")
    @Column(name = "phone_number")
    private Map<String, String> phoneNumbers;

    public Activity(String name) {
        this.name = name;
    }

    public Activity(ActivityType activityType, String name, LocalDateTime startTime) {
        this.activityType = activityType;
        this.name = name;
        this.startTime = startTime;
    }
}
