package meetingroom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingRoomsRepositoryIT {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    MeetingRoomsRepository repository = new MeetingRoomsRepository(factory);

    @Test
    @DisplayName("Save & List")
    void testSaveThenList() {
        repository.save("Jupiter", 20, 25);
        repository.save("Neptunus", 15, 20);
        List<String> names = repository.getMeetingRoomsOrderedByName();
        assertEquals(List.of("Jupiter", "Neptunus"), names);
    }

    @Test
    void getMeetingRoomsOrderedByName() {
        EntityManager em = factory.createEntityManager();
        List<String> names = em.createQuery("select mr.name from MeetingRoom mr order by mr.name", String.class)
                .getResultList();
        em.close();
        return names;
    }
}