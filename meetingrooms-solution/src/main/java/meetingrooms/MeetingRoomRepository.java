package meetingrooms;

import java.util.List;

public interface MeetingRoomRepository {

    void saveMeetingRoom(MeetingRoom meetingRoom);

    List<MeetingRoom> listNames();

    List<MeetingRoom> listNamesReverse();

    List<MeetingRoom> listEverySecondMeetingRoom();

    List<MeetingRoom> listMeetingRoomBySize();

    List<MeetingRoom> searchByName(String name);

    List<MeetingRoom> searchByNamePart(String namePart);

    List<MeetingRoom> searchBySize(int size);

    void deleteMeetingRoom(String name);
}
