package activities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LocationDaoTest {

    private LocationDao locationDao;

    private ActivityDao activityDao;

    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        locationDao = new LocationDao(emf);
        activityDao = new ActivityDao(emf);
    }

    @Test
    void testSaveLocation() {
        locationDao.saveLocation(new Location(100L));
        Location location = locationDao.findLocationNumber(100);
        assertEquals(100, location.getNumber());
    }

//    @Test
//    void testSaveActivityWithLocation() {
//        Location location = new Location(100L);
//        locationDao.saveLocation(location);
//        Activity activity = new Activity("Cycling");
//        activity.setLocation(location);
//        activityDao.saveActivity(activity);
//        Activity anotherActivity = activityDao.findActivityById(activity.getId());
//        assertEquals(100, anotherActivity.getLocation().getNumber());
//    }

    @Test
    void testSaveActivityWithLocation() {
        TrackPoint trackPoint1 = new TrackPoint(1L, LocalDateTime.of(2021,7,20, 9, 30));
        TrackPoint trackPoint2 = new TrackPoint(2L, LocalDateTime.of(2021,6,10, 19, 10));
        Activity activity = new Activity("Cycling");
        activity.setTrackPoints(List.of(trackPoint1, trackPoint2));
        activityDao.saveActivity(activity);

        
        activityDao.saveActivity(activity);
        Activity anotherActivity = activityDao.findActivityById(activity.getId());
        assertEquals(100, anotherActivity.getLocation().getNumber());
    }

    @Test
    public void testPhoneNumber() {
        PhoneNumber phoneNumberHome = new PhoneNumber("home", Collections.singletonList("1234"));
        PhoneNumber phoneNumberWork = new PhoneNumber("work", Collections.singletonList("4321"));
        Activity activity = new Activity("Hiking");
        activity.addPhoneNumber(phoneNumberHome);
        activity.addPhoneNumber(phoneNumberWork);
        activityDao.saveActivity(activity);
        Activity anotherActivity = activityDao.findActivityByIdWithPhoneNumbers(activity.getId());
        assertEquals(2, anotherActivity.getPhoneNumbers().size());
    }

    @Test
    public void testAddPhoneNumber() {
        Activity activity = new Activity("Running");
        activityDao.saveActivity(activity);
        activityDao.addPhoneNumber(activity.getId(), new PhoneNumber("home", Collections.singletonList("1111")));
        Activity anotherActivity = activityDao.findActivityByIdWithPhoneNumbers(activity.getId());
        assertEquals(1, anotherActivity.getPhoneNumbers().size());
    }

}