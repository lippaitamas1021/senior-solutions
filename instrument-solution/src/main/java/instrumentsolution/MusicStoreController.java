package instrumentsolution;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/instruments")
public class MusicStoreController {

    private MusicStoreService musicStoreService;

    public MusicStoreController(MusicStoreService musicStoreService) {
        this.musicStoreService = musicStoreService;
    }

    @GetMapping
    public List<InstrumentDto> getInstruments(@RequestParam Optional<String> brand,
                                              @RequestParam Optional<Integer> price) {
        return musicStoreService.getInstruments(brand, price);
    }

    @GetMapping("/{id}")
    public InstrumentDto getInstrumentById(@PathVariable("id") long id) {
        return musicStoreService.getInstrument(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentDto createInstrument(@Valid @RequestBody CreateInstrumentCommand command) {
        return musicStoreService.createInstrument(command);
    }

    @PutMapping("/{id}")
    public InstrumentDto updatePrice(@PathVariable("id") long id, @Valid @RequestBody UpdatePriceCommand command) {
        return musicStoreService.updatePrice(id, command);
    }

    @DeleteMapping
    public void deleteAll() {
        musicStoreService.deleteAll();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Problem> handleExceptionWhenNotFound(IllegalArgumentException iae) {
        Problem problem = Problem.builder()
                .withType(URI.create("instruments/not-found"))
                .withTitle("No instrument found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problem);
    }
}
