package activities;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = ActivityConfiguration.class)
//@Sql(scripts = "classpath/clear.sql")
public class ActivityDaoTest {

    ActivityDao activityDao;

    @BeforeEach
    void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(factory);
    }

    @Test
    void testSaveThanFind() {
        Activity activity = new Activity(LocalDateTime.of(2021, 7, 13, 14, 55),
                "gyors kör a tó körül", ActivityType.RUNNING);
        activityDao.saveActivity(activity);
        Activity expected = activityDao.findActivityById(activity.getId());

        assertEquals("gyors kör a tó körül", expected.getDescription());
    }

    @Test
    void testSaveThanListAll() {
        activityDao.saveActivity(new Activity(LocalDateTime.of(2021, 7, 13, 14, 55),
                "gyors kör a tó körül", ActivityType.RUNNING));
        activityDao.saveActivity(new Activity(LocalDateTime.of(2021, 7, 17, 6, 0),
                "hajnali bicózás az erdőben", ActivityType.BIKING));
        List<Activity> expected = activityDao.listActivities();

        assertEquals("gyors kör a tó körül", expected.get(0).getDescription());
        assertEquals("hajnali bicózás az erdőben", expected.get(1).getDescription());
    }

    @Test
    void testUpdateActivity() {
        Activity activity = new Activity(LocalDateTime.of(2021, 7, 13, 14, 55),
                "gyors kör a tó körül", ActivityType.RUNNING);
        activityDao.saveActivity(activity);
        Activity expected = activityDao.findActivityById(activity.getId());

        assertEquals("gyors kör a tó körül", expected.getDescription());

        activityDao.updateActivity(activity.getId(), "Futáááás!");
        Activity modified = activityDao.findActivityById(activity.getId());

        assertEquals("Futáááás!", modified.getDescription());
    }

    @Test
    void testFindActivityByIdWithLabels() {
        Activity activity = new Activity(LocalDateTime.of(2021, 7, 13, 14, 55),
                "gyors kör a tó körül", ActivityType.RUNNING);
        activity.setLabels(Arrays.asList("déli futás", "Szelidi-tó"));
        activityDao.saveActivity(activity);

        Activity expected = activityDao.findActivityByIdWithLabels(activity.getId());

        assertEquals(Arrays.asList("déli futás", "Szelidi-tó"), expected.getLabels());
    }

    @Test
    void testFindActivityByIdWithTrackPoints() {
        Activity activity = new Activity(LocalDateTime.of(2021, 7, 13, 14, 55),
                "gyors kör a tó körül", ActivityType.RUNNING);
        activity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 2, 3, 4, 5), 47.497912, 19.040235));
        activity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 4, 5, 6, 7), -33.88223, 151.33140));
        activity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 3, 4, 5, 6), 48.87376, 2.25120));

        activityDao.saveActivity(activity);

        Activity expected = activityDao.findActivityByIdWithTrackPoints(activity.getId());

        assertEquals(3, expected.getTrackPoints().size());
        assertEquals(-33.88223, expected.getTrackPoints().get(2).getLat());
        assertEquals(2.25120, expected.getTrackPoints().get(1).getLon());
    }

    @Test
    void testFindTrackPointCoordinatesByDate() {
        Activity activity1 = new Activity(LocalDateTime.of(2021, 7, 13, 14, 55),
                "gyors kör a tó körül", ActivityType.RUNNING);
        activity1.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 2, 3, 4, 5), 47.497912, 19.040235));
        activity1.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 4, 5, 6, 7), -33.88223, 151.33140));
        activity1.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 3, 4, 5, 6), 48.87376, 2.25120));

        activityDao.saveActivity(activity1);

        Activity activity2 = new Activity(LocalDateTime.of(2021, 7, 17, 6, 0),
                "hajnali bicózás az erdőben", ActivityType.BIKING);
        activity2.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 2, 3, 4, 5), 47.497912, 19.040235));
        activity2.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 4, 5, 6, 7), -33.88223, 151.33140));
        activity2.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 3, 4, 5, 6), 48.87376, 2.25120));

        activityDao.saveActivity(activity2);

        Activity activity3 = new Activity(LocalDateTime.of(2018, 7, 15, 19, 15),
                "esti levezetés", ActivityType.RUNNING);
        activity3.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 2, 3, 4, 5), 47.497912, 19.040235));
        activity3.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 4, 5, 6, 7), -33.88223, 151.33140));
        activity3.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 3, 4, 5, 6), 48.87376, 2.25120));

        activityDao.saveActivity(activity3);

        List<Coordinate> expected = activityDao.findTrackPointCoordinatesByDate(LocalDateTime.of(2019, 6, 7, 8, 9), 1, 20);

        assertEquals(3, expected.size());
    }

    @Test
    void testFindTrackPointCountByActivity() {
        Activity activity1 = new Activity(LocalDateTime.of(2021, 7, 13, 14, 55),
                "gyors kör a tó körül", ActivityType.RUNNING);
        activity1.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 2, 3, 4, 5), 47.497912, 19.040235));
        activity1.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 4, 5, 6, 7), -33.88223, 151.33140));

        activityDao.saveActivity(activity1);

        Activity activity2 = new Activity(LocalDateTime.of(2021, 7, 17, 6, 0),
                "hajnali bicózás az erdőben", ActivityType.BIKING);
        activity2.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 2, 3, 4, 5), 47.497912, 19.040235));

        activityDao.saveActivity(activity2);

        Activity activity3 = new Activity(LocalDateTime.of(2021, 7, 15, 19, 15),
                "esti levezetés", ActivityType.RUNNING);
        activity3.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 2, 3, 4, 5), 47.497912, 19.040235));
        activity3.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 4, 5, 6, 7), -33.88223, 151.33140));
        activity3.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 3, 4, 5, 6), 48.87376, 2.25120));

        activityDao.saveActivity(activity3);

        List<Object[]> expected = activityDao.findTrackPointCountByActivity();

        Object[] dataOfActivity1 = new Object[]{"gyors kör a tó körül", 2};
        Object[] dataOfActivity2 = new Object[]{"hajnali bicózás az erdőben", 1};
        Object[] dataOfActivity3 = new Object[]{"esti levezetés", 3};

        assertEquals(3, expected.size());
        assertArrayEquals(dataOfActivity1, expected.get(0));
        assertArrayEquals(dataOfActivity2, expected.get(2));
        assertArrayEquals(dataOfActivity3, expected.get(1));
    }

//    @Autowired
//    private ActivityRepository activityRepository;
//
//    @Autowired
//    private ActivityDao activityDao;
//
//    private LocationDao locationDao;
//
////    @BeforeEach
////    public void init() {
////        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
////        activityDao = new ActivityDao(emf);
////        locationDao = new LocationDao(emf);
////    }
//
//    @Test
//    public void testFindByNameStartingWith() {
//        activityRepository.save(new Activity("Debug"));
//        List<Activity> activities = activityRepository.findByNameStartingWith("De");
//        assertEquals(1, activities.size());
//        assertEquals("Debug", activities.get(0).getName());
//    }
//
//
//    @Test
//    public void testFindByNameLength() {
//        activityRepository.save(new Activity("Debug"));
//        activityRepository.save(new Activity("Study"));
//        List<Activity> activities = activityRepository.findByNameLength(5);
//        assertEquals(2, activities.size());
//        assertEquals("Debug", activities.get(0).getName());
//    }
//
//    @Test
//    public void testFindByNameIgnoreCase() {
//        activityRepository.save(new Activity("Debug"));
//        activityRepository.save(new Activity("Study"));
//        List<Activity> activities = activityRepository.findByNameIgnoreCase("debug");
//        assertEquals(2, activities.size());
//        assertEquals("Debug", activities.get(0).getName());
//    }
//
//    @Test
//    public void testFindAllPageable() {
//        for (int i = 100; i < 130; i++) {
//            String name = "Debug" + i;
//            activityRepository.save(new Activity(name));
//        }
//        Page<Activity> page = activityRepository.findAll(PageRequest.of(2, 10, Sort.by("name")));
//        assertEquals(30, page.getTotalElements());
//        assertEquals(2, page.getNumber());
//        assertEquals(10, page.getNumberOfElements());
//        assertEquals("Debug 120", page.getContent().get(0).getName());
//        assertEquals("Debug 129", page.getContent().get(9).getName());
//    }
//
//    @Test
//    public void testSaveThenListRepository() {
//        activityRepository.save(new Activity("Debug"));
//        activityRepository.save(new Activity("Learning"));
//        List<String> names = StreamSupport.stream(activityRepository.findAll().spliterator(), false)
//                .map(Activity::getName).collect(Collectors.toList());
//        assertEquals(List.of("Debug"), names);
//    }
//
//    @Test
//    public void testSaveThenFindById() {
//        Activity activity = new Activity("Biking");
//        activityDao.saveActivity(activity);
//        long id = activity.getId();
//        Activity anotherActivity = activityDao.findActivityById(id);
//        assertEquals("Biking", anotherActivity.getName());
//    }
//
//    @Test
//    public void testSaveThenListAll() {
//        activityDao.saveActivity(new Activity("Hiking"));
//        activityDao.saveActivity(new Activity("Running"));
//        List<Activity> activities = activityDao.listActivities();
//        assertEquals(List.of("Hiking", "Running"), activities.stream().map(Activity::getName).collect(Collectors.toList()));
//    }
//
//    @Test
//    public void testChangeName() {
//        Activity activity = new Activity("Playing basketball");
//        activityDao.saveActivity(activity);
//        long id = activity.getId();
//        activityDao.changeName(id, "Hiking in the forest");
//        Activity anotherActivity = activityDao.findActivityById(id);
//        assertEquals("Hiking in the forest", anotherActivity.getName());
//    }
//
//    @Test
//    public void testDelete() {
//        Activity activity = new Activity("Running");
//        activityDao.saveActivity(activity);
//        long id = activity.getId();
//        activityDao.deleteActivity(id);
//        List<Activity> activities = activityDao.listActivities();
//        assertTrue(activities.isEmpty());
//    }
//
//    @Test
//    public void testWithWrongId() {
//        Activity activity = activityDao.findActivityById(21L);
//        assertNull(activity);
//    }
//
//    @Test
//    public void testActivityWithAttributes() {
//        for(int i = 0; i < 10; i++) {
//            activityDao.saveActivity(new Activity(LocalDateTime.of(2021, 7, 14, 15, 30),
//                    ActivityType.HIKING));
//        }
//        Activity activity = activityDao.listActivities().get(0);
//        assertEquals(LocalDateTime.of(2021,7,14, 15, 30), activity.getStartTime());
//    }
//
//    @Test
//    public void testSaveActivityAndChangeState() {
//        Activity activity = new Activity("Bikling");
//        activityDao.saveActivity(activity);
//        activity.setName("Biking");
//        Activity modifiedActivity = activityDao.findActivityById(activity.getId());
//        assertEquals("Biking", modifiedActivity.getName());
//        assertNotSame(activity, modifiedActivity);
//    }
//
//    @Test
//    public void testMerge() {
//        Activity activity = new Activity("debuging");
//        activityDao.saveActivity(activity);
//        activity.setName("debugging");
//        activityDao.updateActivity(activity.getId(), activity.getDescription());
//        Activity modifiedActivity = activityDao.findActivityById(activity.getId());
//        assertEquals("debugging", modifiedActivity.getName());
//    }
//
//    @Test
//    public void testFlush() {
//        for (int i = 0; i < 10; i++) {
//            activityDao.saveActivity(new Activity("Running in group" + i));
//        }
//        activityDao.updateEActivityNames();
//    }
//
//    @Test
//    public void testUpdateActivity() {
//        Activity activity = new Activity("Biking on road");
//        activityDao.saveActivity(activity);
//        Activity expected = activityDao.findActivityById(activity.getId());
//        assertEquals("Biking on road", expected.getDescription());
//        activityDao.updateActivity(activity.getId(), "Preparing for the Tour de France");
//        Activity modifiedActivity = activityDao.findActivityById(activity.getId());
//        assertEquals("Preparing for the Tour de France", modifiedActivity.getDescription());
//    }
//
//    @Test
//    public void testFindActivityByIdWithLabels() {
//        Activity activity = new Activity(LocalDateTime.of(2021,7,20,10,0),
//                ActivityType.BASKETBALL);
//        activity.setLabels(Arrays.asList("játszótér", "Debrecen"));
//        activityDao.saveActivity(activity);
//        Activity expected = activityDao.findActivityByIdWithLabels(activity.getId());
//        assertEquals(Arrays.asList("játszótér", "Debrecen"), expected.getLabels());
//    }
//
//    @Test
//    public void testNickNames() {
//        Activity activity = new Activity("Horgászás");
//        activity.setNickNames(Set.of("Pecázás"));
//        activityDao.saveActivity(activity);
//        Activity anotherActivity = activityDao.findActivityByIdWithNickNames(activity.getId());
//        assertEquals(Set.of("Pecázás"), anotherActivity.getNickNames());
//    }
//
//    @Test
//    public void testActivityEntry() {
//        Activity activity = new Activity("Running at Margaret Island");
//        activity.setActivityBookings(Set.of(new ActivityBookings(LocalDate.of(2021,7,31), 1),
//                new ActivityBookings(LocalDate.of(2021,9,1), 2)));
//        activityDao.saveActivity(activity);
//        Activity anotherActivity = activityDao.findActivityByIdWithEntries(activity.getId());
//        assertEquals(2, anotherActivity.getActivityBookings().size());
//    }
//
//    @Test
//    public void testPhoneNumbers() {
//        Activity activity = new Activity("Basketball match");
//        activity.setPhoneNumbers(Map.of("Johnny", "123456789", "Tommy", "234567890"));
//        activityDao.saveActivity(activity);
//        Activity anotherActivity = activityDao.findActivityByIdWithPhoneNumbers(activity.getId());
//        assertEquals("123456789", anotherActivity.getPhoneNumbers().get(0));
//        assertEquals("234567890", anotherActivity.getPhoneNumbers().get(1));
//    }
//
//    @Test
//    public void testFindActivityByName() {
//        activityDao.saveActivity(new Activity("learning"));
//        Activity activity = activityDao.findActivityByName("learning");
//        assertEquals("learning", activity.getName());
//    }
//
//    @Test
//    public void testPaging() {
//        for (int i = 100; i < 300; i++) {
//            Activity activity = new Activity("Debugging" + i);
//            activityDao.saveActivity(activity);
//        }
//        List<Activity> activities = activityDao.lisActivities(50, 20);
//        assertEquals("Debugging 150", activities.get(0).getName());
//        assertEquals(20, activities.size());
//    }
//
//    @Test
//    public void testFindNumber() {
//        Activity activity = new Activity("Debugging");
//        Location location = new Location(1L, 11);
//        locationDao.saveLocation(location);
//        activity.setLocation(location);
//        activityDao.saveActivity(activity);
//        int number = activityDao.findLocationNumberByActivityName("Debugging");
//        assertEquals(11, number);
//    }
//
//    @Test
//    public void testBaseData() {
//        Activity activity = new Activity("Debugging");
//        activityDao.saveActivity(activity);
//        List<Object[]> data = activityDao.listActivityBaseData();
//        assertEquals(1, data.size());
//        assertEquals("Debugging", data.get(0)[1]);
//    }
//
//    @Test
//    public void testDto() {
//        activityDao.saveActivity(new Activity("Debugging"));
//        activityDao.saveActivity(new Activity("Learning"));
//        List<ActBaseDataDto> data = activityDao.listActivityDto();
//        assertEquals(2, data.size());
//        assertEquals("Learning", data.get(1).getName());
//    }
}
