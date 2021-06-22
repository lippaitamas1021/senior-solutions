package locations;

public class LocationParser {

    public Location parse(String text) {
        String[] temp = text.split(",");
        return new Location(temp[0], Double.parseDouble(temp[1]), Double.parseDouble(temp[2]));
    }
}