package activities;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActivityDAOTest {

    private ActivityDAO activityDAO;

    @BeforeEach
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        activityDAO = new ActivityDAO(emf);
    }

    @Test
    public void testSaveThenFindById() {
        Activity activity = new Activity("Biking");
        activityDAO.save(activity);
        long id = activity.getId();
        Activity anotherActivity = activityDAO.findActivityById(id);
        assertEquals("Biking", anotherActivity.getName());
    }

    @Test
    public void testSaveThenListAll() {
        activityDAO.save(new Activity("Hiking"));
        activityDAO.save(new Activity("Running"));
        List<Activity> activities = activityDAO.listActivities();
        assertEquals(List.of("Hiking", "Running"), activities.stream().map(Activity::getName).collect(Collectors.toList()));
    }

    @Test
    public void testChangeName() {
        Activity activity = new Activity("Playing basketball");
        activityDAO.save(activity);
        long id = activity.getId();
        activityDAO.changeName(id, "Hiking in the forest");
        Activity anotherActivity = activityDAO.findActivityById(id);
        assertEquals("Hiking in the forest", anotherActivity.getName());
    }

    @Test
    public void testDelete() {
        Activity activity = new Activity("Running");
        activityDAO.save(activity);
        long id = activity.getId();
        activityDAO.deleteActivity(id);
        List<Activity> activities = activityDAO.listActivities();
        assertTrue(activities.isEmpty());
    }

    @Test
    public void testWithWrongId() {
        Activity activity = activityDAO.findActivityById(21L);
        assertNull(activity);
    }

    @Test
    public void testActivityWithAttributes() {
        for(int i = 0; i < 10; i++) {
            activityDAO.save(new Activity(ActivityType.RUNNING, "Hiking", LocalDate.of(2021, 7, 14)));
        }
        Activity activity = activityDAO.listActivities().get(0);
        assertEquals(LocalDate.of(2021,7,14), activity.getStartTime());
    }

    @Test
    public void saveActivityAndChangeState() {
        Activity activity = new Activity("Bikling");
        activityDAO.save(activity);
        activity.setName("Biking");
        Activity modifiedActivity = activityDAO.findActivityById(activity.getId());
        assertEquals("Biking", modifiedActivity.getName());
        assertNotSame(activity, modifiedActivity);
    }

    @Test
    public void testMerge() {
        Activity activity = new Activity("debuging");
        activityDAO.save(activity);
        activity.setName("debugging");
        activityDAO.updateActivity(activity);
        Activity modifiedActivity = activityDAO.findActivityById(activity.getId());
        assertEquals("debugging", modifiedActivity.getName());
    }

    @Test
    public void testFlush() {
        for (int i = 0; i < 10; i++) {
            activityDAO.save(new Activity("singing" + i));
        }
        activityDAO.updateEActivityNames();
    }
}