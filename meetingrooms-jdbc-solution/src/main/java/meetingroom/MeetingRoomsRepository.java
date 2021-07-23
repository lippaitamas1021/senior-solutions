package meetingroom;

import lombok.AllArgsConstructor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@AllArgsConstructor
public class MeetingRoomsRepository implements MeetingRooms {

    private EntityManagerFactory factory;

    public MeetingRoom save(String name, int width, int length) {
        MeetingRoom meetingRoom = new MeetingRoom(name, width, length);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(meetingRoom);
        em.getTransaction().commit();
        em.close();
        return meetingRoom;
    }

    public List<String> getMeetingRoomsOrderedByName() {
        EntityManager em = factory.createEntityManager();
        List<String> meetingRoomsNames = em.createNamedQuery("getAllOrderedByName", String.class)
                .getResultList();
        em.close();
        return meetingRoomsNames;
    }
}
