package locations;

import java.util.Optional;

public class DistanceService {

    private LocationRepository locationRepository;

    public DistanceService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    Optional<Double> calculateDistance(String name1, String name2) {
        double value = 0.0;
        Optional<Location> findFirstLocation = locationRepository.findByName(name1);
        Optional<Location> findSecondLocation = locationRepository.findByName(name2);
        if (findFirstLocation.equals(Optional.empty()) || findSecondLocation.equals(Optional.empty())) {
            return Optional.empty();
        } else {
            if (findFirstLocation.isPresent() && findSecondLocation.isPresent()) {
                value = findFirstLocation.get().distanceFrom(findSecondLocation.get());
            }
        }
        return Optional.of(value);
    }
}
