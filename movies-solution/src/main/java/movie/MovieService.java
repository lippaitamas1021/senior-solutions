package movie;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private List<Movie> movies = new ArrayList<>();

    public List<Movie> listMovies() {
        return new ArrayList<>(movies);
    }

    public void save(Movie movie) {
        movies.add(movie);
    }

    public Movie getLatestMovie() {
        Movie result = movies.get(0);
        for (Movie m : movies) {
            if (m.getReleaseDate().isAfter(result.getReleaseDate())) {
                result = m;
            }
        }
        return result;
    }
    public List<Movie> searchByPartOfTitle(String part) {
        return movies.stream()
                .filter(m -> m.getName().contains(part))
                .collect(Collectors.toList());
    }
}
