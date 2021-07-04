package moviesolution;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping
    public MovieDto createMovie(@RequestBody CreateMovieCommand command) {
        return movieService.createMovie(command);
    }

    @PostMapping("/{id}/rating")
    public MovieDto updateMovieRating(@PathVariable("id") long id, @RequestBody UpdateMovieRatingCommand command) {
        return movieService.addRating(id, command);
    }
}
