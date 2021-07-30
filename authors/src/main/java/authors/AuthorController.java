package authors;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDto> getAuthors() {
        return authorService.getAuthors();
    }

    @PostMapping
    public AuthorDto createAuthor(@RequestBody CreateAuthorCommand command) {
        return authorService.createAuthor(command);
    }

    @PostMapping("/{id}/books")
    public AuthorDto addBook(@PathVariable ("id") long id, @RequestBody AddBookCommand command) {
        return authorService.addBook(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") long id) {
        authorService.deleteAuthor(id);
    }
}
