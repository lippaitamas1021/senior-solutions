package moviesolution;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieService {

    private List<Movie> movies = new ArrayList<>();
    private AtomicLong idGen = new AtomicLong();
    private ModelMapper modelMapper;

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> getMovies() {
        Type targetListType = new TypeToken<List<MovieDto>>(){}.getType();
        return modelMapper.map(movies, targetListType);
    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(idGen.incrementAndGet(), command.getTitle(), command.getLength());
        movies.add(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    private Movie findById(long id) {
        return movies.stream().filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
    }

    public MovieDto addRating(long id, UpdateMovieRatingCommand command) {
        Movie movie = findById(id);
        movie.addRating(command.getRating());
        return modelMapper.map(movie, MovieDto.class);
    }
}
