package locations;

import java.util.Objects;

public class Location {

    private String name;
    private double lat;
    private double lon;

    public Location() {
    }

    public Location(String name, double lat, double lon) {
        this.name = name;
        if (-90 <= lat && lat <= 90) {
            this.lat = lat;
        } else {
            throw new IllegalStateException("The lattitude must be between -90 & 90");
        }
        if (-180 < lon && lon < 180) {
            this.lon = lon;
        } else {
            throw new IllegalStateException("The longitude must be between -180 & 180");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double distanceFrom(Location another) {
        final int R = 6371;
        double latDistance = Math.toRadians(another.getLat() - this.getLat());
        double lonDistance = Math.toRadians(another.getLon() - this.getLon());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.getLat())) * Math.cos(Math.toRadians(another.getLat()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        double height = 0;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.lat, lat) == 0 && Double.compare(location.lon, lon) == 0 && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lat, lon);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}