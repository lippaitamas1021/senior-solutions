package meetingrooms;

import java.util.List;

public class MeetingRoomService {

    private MeetingRoomRepository meetingRoomRepository;
    public MeetingRoomService(MeetingRoomRepository meetingRoomRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
    }
    public void saveMeetingRoom(MeetingRoom meetingRoom) {
        meetingRoomRepository.saveMeetingRoom(meetingRoom);
    }
    public List<MeetingRoom> listNames() {
        return meetingRoomRepository.listNames();
    }
    public List<MeetingRoom> listNamesReverse() {return meetingRoomRepository.listNamesReverse(); }
    public List<MeetingRoom> listEverySecondMeetingRoom() {
//        List<String> ordered = meetingRoomRepository.listNames();
//        return IntStream.range(0, ordered.size())
//                .filter(i -> i % 2 != 0)
//                .mapToObj(n -> ordered.get(n))
//                .collect(Collectors.toList());
        return meetingRoomRepository.listEverySecondMeetingRoom();
    }
    public List<MeetingRoom> listMeetingRoomSize() {
        return meetingRoomRepository.listMeetingRoomBySize();
    }
    public List<MeetingRoom> searchByName(String name) {
        return meetingRoomRepository.listMeetingRoomBySize();
    }
    public List<MeetingRoom> searchByNamePart(String namePart) {return meetingRoomRepository.searchByNamePart(namePart); }
    public List<MeetingRoom> searchBySize(int size) {
        return meetingRoomRepository.searchBySize(size);
    }
    public void deleteMeetingRoom(String name) {
        meetingRoomRepository.deleteMeetingRoom(name);
    }
}
