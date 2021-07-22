package activities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {

    private ActivityDao activityDao;

    @BeforeEach
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(emf);
    }
    @Test
    public void testSaveThenFindById() {
        Activity activity = new Activity("Biking");
        activityDao.save(activity);
        long id = activity.getId();
        Activity anotherActivity = activityDao.findActivityById(id);
        assertEquals("Biking", anotherActivity.getName());
    }
    @Test
    public void testSaveThenListAll() {
        activityDao.save(new Activity("Hiking"));
        activityDao.save(new Activity("Running"));
        List<Activity> activities = activityDao.listActivities();
        assertEquals(List.of("Hiking", "Running"), activities.stream().map(Activity::getName).collect(Collectors.toList()));
    }
    @Test
    public void testChangeName() {
        Activity activity = new Activity("Playing basketball");
        activityDao.save(activity);
        long id = activity.getId();
        activityDao.changeName(id, "Hiking in the forest");
        Activity anotherActivity = activityDao.findActivityById(id);
        assertEquals("Hiking in the forest", anotherActivity.getName());
    }
    @Test
    public void testDelete() {
        Activity activity = new Activity("Running");
        activityDao.save(activity);
        long id = activity.getId();
        activityDao.deleteActivity(id);
        List<Activity> activities = activityDao.listActivities();
        assertTrue(activities.isEmpty());
    }
    @Test
    public void testWithWrongId() {
        Activity activity = activityDao.findActivityById(21L);
        assertNull(activity);
    }
    @Test
    public void testActivityWithAttributes() {
        for(int i = 0; i < 10; i++) {
            activityDao.save(new Activity(ActivityType.RUNNING, "Hiking", LocalDateTime.of(2021, 7, 14, 15, 30)));
        }
        Activity activity = activityDao.listActivities().get(0);
        assertEquals(LocalDateTime.of(2021,7,14, 15, 30), activity.getStartTime());
    }
    @Test
    public void testSaveActivityAndChangeState() {
        Activity activity = new Activity("Bikling");
        activityDao.save(activity);
        activity.setName("Biking");
        Activity modifiedActivity = activityDao.findActivityById(activity.getId());
        assertEquals("Biking", modifiedActivity.getName());
        assertNotSame(activity, modifiedActivity);
    }
    @Test
    public void testMerge() {
        Activity activity = new Activity("debuging");
        activityDao.save(activity);
        activity.setName("debugging");
        activityDao.updateActivity(activity);
        Activity modifiedActivity = activityDao.findActivityById(activity.getId());
        assertEquals("debugging", modifiedActivity.getName());
    }
    @Test
    public void testFlush() {
        for (int i = 0; i < 10; i++) {
            activityDao.save(new Activity("Running in group" + i));
        }
        activityDao.updateEActivityNames();
    }
    @Test
    public void testUpdateActivity() {
        Activity activity = new Activity("Biking on road");
        activityDao.save(activity);
        Activity expected = activityDao.findActivityById(activity.getId());
        assertEquals("Biking on road", expected.getDescription());
        activityDao.updateActivity(activity.getId(), "Preparing for the Tour de France");
        Activity modifiedActivity = activityDao.findActivityById(activity.getId());
        assertEquals("Preparing for the Tour de France", modifiedActivity.getDescription());
    }
    @Test
    public void testFindActivityByIdWithLabels() {
        Activity activity = new Activity(ActivityType.BASKETBALL,"Playing basketball", LocalDateTime.of(
                2021,7,20,10,0));
        activity.setLabels(Arrays.asList("játszótér", "Debrecen"));
        activityDao.save(activity);
        Activity expected = activityDao.findActivityByIdWithLabels(activity.getId());
        assertEquals(Arrays.asList("játszótér", "Debrecen"), expected.getLabels());
    }
    @Test
    public void testNickNames() {
        Activity activity = new Activity("Horgászás");
        activity.setNickNames(Set.of("Pecázás"));
        activityDao.save(activity);
        Activity anotherActivity = activityDao.findActivityByIdWithNickNames(activity.getId());
        assertEquals(Set.of("Pecázás"), anotherActivity.getNickNames());
    }
    @Test
    public void testActivityEntry() {
        Activity activity = new Activity("Running at Margaret Island");
        activity.setActivityBookings(Set.of(new ActivityBookings(LocalDate.of(2021,7,31), 1),
                new ActivityBookings(LocalDate.of(2021,9,1), 2)));
        activityDao.save(activity);
        Activity anotherActivity = activityDao.findActivityByIdWithEntries(activity.getId());
        assertEquals(2, anotherActivity.getActivityBookings().size());
    }
    @Test
    public void testPhoneNumbers() {
        Activity activity = new Activity("Basketball match");
        activity.setPhoneNumbers(Map.of("Johnny", "123456789", "Tommy", "234567890"));
        activityDao.save(activity);
        Activity anotherActivity = activityDao.findActivityByIdWithPhoneNumbers(activity.getId());
        assertEquals("123456789", anotherActivity.getPhoneNumbers().get("Johnny"));
        assertEquals("234567890", anotherActivity.getPhoneNumbers().get("Tommy"));
    }

}