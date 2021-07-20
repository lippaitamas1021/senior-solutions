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
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = factory.createEntityManager();
        activityDAO = new ActivityDAO(factory);
    }

    @Test
    void testSaveThenFindById() {
        Activity activity = new Activity("x", 5L, "driving");
        activityDAO.save(activity);
        long id = activity.getId();
        Activity anotherActivity = activityDAO.findById("x", id);
        assertEquals("driving", anotherActivity.getName());
    }

    @Test
    void testSaveThenListAll() {
        activityDAO.save(new Activity("x", 1L, "riding"));
        activityDAO.save(new Activity("y", 2L, "skiing"));
        List<Activity> activities = activityDAO.listAll();
        assertEquals(List.of("riding", "skiing"), activities.stream().map(Activity::getName).collect(Collectors.toList()));
    }

    @Test
    public void testChangeName() {
        Activity activity = new Activity("z", 3L, "driving");
        activityDAO.save(activity);
        long id = activity.getId();
        activityDAO.changeName("v", 4L, "diving");
        Activity anotherActivity = activityDAO.findById("v", 4L);
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
        Activity activity = activityDAO.findById("m", 21);
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
}