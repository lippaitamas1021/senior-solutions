package locations;

public class LocationParser {

    public Location parse(String text) {
        try {
            String[] temp = text.split(",");
            return new Location(temp[0], Double.parseDouble(temp[1]), Double.parseDouble(temp[2]));
        } catch (Exception e) {
            throw new IllegalArgumentException("Wrong parameters", e);
        }
    }

    public boolean isOnEquator(Location location) {
        return location.getLat() == 0;
    }

    public boolean isOnPrimeMeridian(Location location) {
        return location.getLon() == 0;
    }
}