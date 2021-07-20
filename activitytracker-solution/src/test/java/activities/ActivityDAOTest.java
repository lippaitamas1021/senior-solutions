package activities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

class ActivityDAOTest {

    private ActivityDAO activityDAO;

    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        activityDAO = new ActivityDAO(emf);
    }

    @Test
    void testSaveThenFindById() {
        Activity activity = new Activity("driving");
        activityDAO.save(activity);
        long id = activity.getId();
        Activity anotherActivity = activityDAO.findById(id);
        assertEquals("driving", anotherActivity.getName());
    }

    @Test
    void testSaveThenListAll() {
        activityDAO.save(new Activity("riding"));
        activityDAO.save(new Activity("skiing"));
        List<Activity> activities = activityDAO.listAll();
        assertEquals(List.of("riding", "skiing"), activities.stream().map(Activity::getName).collect(Collectors.toList()));
    }

    @Test
    public void testChangeName() {
        Activity activity = new Activity("driving");
        activityDAO.save(activity);
        long id = activity.getId();
        activityDAO.changeName(id, "diving");
        Activity anotherActivity = activityDAO.findById(4L);
        assertEquals("diving", anotherActivity.getName());
    }

    @Test
    public void testDelete() {
        Activity activity = new Activity("swimming");
        activityDAO.save(activity);
        long id = activity.getId();
        activityDAO.delete(id);
        List<Activity> activities = activityDAO.listAll();
        assertTrue(activities.isEmpty());
    }

    @Test
    public void testWithWrongId() {
        Activity activity = activityDAO.findById(21);
        assertEquals(null, activity);
    }

    @Test
    public void testActivityWithAttributes() {
        for(int i = 0; i < 10; i++) {
            activityDAO.save(new Activity("learning", ActivityType.STATIC, LocalDate.of(2021, 7, 14)));
        }
        Activity activity = activityDAO.listAll().get(0);
        assertEquals(LocalDate.of(2021,7,14), activity.getDate());
    }

    @Test
    public void saveActivityAndChangeState() {
        Activity activity = new Activity("learning");
        activityDAO.save(activity);
        activity.setName("reading");
        Activity modifiedActivity = activityDAO.findById(activity.getId());
        assertEquals("learning", modifiedActivity.getName());
        assertFalse(activity == modifiedActivity);
    }
}