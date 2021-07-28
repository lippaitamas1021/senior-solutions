package movies;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {

    private MovieRepository movieRepository;

    private ModelMapper modelMapper;

    public List<MovieDto> getMovies() {
        return movieRepository.findAll().stream()
                .map(m -> modelMapper.map(m, MovieDto.class))
                .collect(Collectors.toList());
    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(command.getTitle());
        movieRepository.save(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Transactional
    public MovieDto addRating(long id, CreateRatingCommand command) {
        Movie movie = movieRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        movie.addRating(command.getRating());
        return modelMapper.map(movie, MovieDto.class);
    }
}
