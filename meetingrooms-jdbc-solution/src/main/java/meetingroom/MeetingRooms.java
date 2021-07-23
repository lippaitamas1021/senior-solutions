package meetingroom;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MeetingRooms extends JpaRepository<MeetingRoom, Long> {

    // select mr from MeetingRoom mr where mr.name like :name
    List<MeetingRoom> findAllByNameLike(String name);
}
