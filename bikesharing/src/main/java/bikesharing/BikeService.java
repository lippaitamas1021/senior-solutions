package bikesharing;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class BikeService {

    private List<BikeShare> shares = new ArrayList<>();
    private ModelMapper modelMapper;

    public BikeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<BikeShare> getShares() {
        return shares;
    }

    private void readFromFile() {
        Scanner scanner = new Scanner(Objects.requireNonNull(BikeService.class.getResourceAsStream("/bikes.csv")));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] temp = line.split(";");
                String bikeId = temp[0];
                String userId = temp[1];
                LocalDateTime dateTime = dateTimeParser(temp[2]);
                double distance = Double.parseDouble(temp[3]);
                shares.add(new BikeShare(bikeId, userId, dateTime, distance));
        }
    }

    private LocalDateTime dateTimeParser(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public List<BikeShareDto> getAllShares() {
        listChecker();
        return shares.stream()
                .map(r -> modelMapper.map(r, BikeShareDto.class))
                .collect(Collectors.toList());
    }

    public List<String> getUserIds() {
        listChecker();
        return shares.stream()
                .map(BikeShare::getUserId)
                .distinct()
                .collect(Collectors.toList());
    }

    private void listChecker() {
        if (shares == null) {
            readFromFile();
        }
    }

    public static void main(String[] args) {
        BikeService bikeService = new BikeService(new ModelMapper());
        bikeService.readFromFile();
        System.out.println(bikeService.shares);
    }
}
