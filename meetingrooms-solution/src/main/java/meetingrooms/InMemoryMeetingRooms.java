package meetingrooms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryMeetingRooms implements MeetingRoomRepository {

    private List<MeetingRoom> meetingRooms = new ArrayList<>();
    public List<MeetingRoom> getMeetingRooms() {
        return meetingRooms;
    }

    public InMemoryMeetingRooms(List<MeetingRoom> meetingRooms) {
        this.meetingRooms = meetingRooms;
    }

    public InMemoryMeetingRooms() {
    }

    @Override
    public void saveMeetingRoom(MeetingRoom meetingRoom) {
        if (meetingRoom != null) {
            meetingRooms.add(meetingRoom);
        }
    }
    @Override
    public List<MeetingRoom> listNames() {
//        meetingRooms.stream().map(MeetingRoom::getName).sorted(Collator.getInstance(new Locale("hu", "HU"))).collect(Collectors.toList());
        List<MeetingRoom> result = new ArrayList<>(meetingRooms);
        result.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return result;
    }

    @Override
    public List<MeetingRoom> listNamesReverse() {
        List<MeetingRoom> result = new ArrayList<>(listNames());
        Collections.reverse(result);
        return result;
    }

    @Override
    public List<MeetingRoom> listEverySecondMeetingRoom() {
        List<MeetingRoom> result = new ArrayList<>();
        for (int i = 0; i < meetingRooms.size(); i = i+2) {
            result.add(meetingRooms.get(i));
        }
        return result;
    }

    @Override
    public List<MeetingRoom> listMeetingRoomBySize() {
        List<MeetingRoom> rooms = new ArrayList<>(meetingRooms);
        List<MeetingRoom> result = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (MeetingRoom room : rooms) {
            if (room.countArea() < min) {
                result.add(room);
            }
        }
        Collections.reverse(result);
        return result;
    }

    @Override
    public List<MeetingRoom> searchByName(String name) {
        if (name != null) {
            List<MeetingRoom> result = new ArrayList<>();
            for (MeetingRoom meetingRoom : meetingRooms) {
                if (meetingRoom.getName().equals(name)) {
                    result.add(meetingRoom);
                }
            }
            if (result.isEmpty()) {
                throw new IllegalArgumentException("Name must be given");
            } else {
                return result;
            }
        } else {
            throw new IllegalArgumentException("Name must be given");
        }
    }

    @Override
    public List<MeetingRoom> searchByNamePart(String namePart) {
        if (namePart != null) {
            List<MeetingRoom> result = new ArrayList<>();
            for (MeetingRoom meetingRoom : meetingRooms) {
                if (meetingRoom.getName().contains(namePart)) {
                    result.add(meetingRoom);
                }
            }
            return result;
        } else {
            throw new IllegalArgumentException("NamePart must be given");
        }
    }

    @Override
    public List<MeetingRoom> searchBySize(int size) {
        List<MeetingRoom> result = new ArrayList<>();
        for (MeetingRoom meetingRoom : meetingRooms) {
            if (size < meetingRoom.countArea()) {
                result.add(meetingRoom);
            }
        }
        return result;
    }

    @Override
    public void deleteMeetingRoom(String name) {
        meetingRooms.removeIf(meetingRoom -> meetingRoom.getName().equals(name));
    }
}
