package locations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocationService {

    private List<Location> locations = new ArrayList<>();

    public void writeLocations(Path file, List<Location> locations) {
        try (BufferedWriter bw = Files.newBufferedWriter(file)) {
            for (Location item : locations) {
                bw.write(item.getName() + "; " + item.getLat() + "; " + item.getLon() + "\n");
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not write the file", ioe);
        }
    }

    public List<Location> readLocations(Path file) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(file)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split(";");
                String name = temp[0];
                double lat = Double.parseDouble(temp[1]);
                double lon = Double.parseDouble(temp[2]);
                locations.add(new Location(name, lat, lon));
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Can not read the file");
        }
        return locations;
    }
}
