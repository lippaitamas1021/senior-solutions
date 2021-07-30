package authors;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {

    private final ModelMapper modelMapper;

    private final AuthorRepository authorRepository;

    public List<AuthorDto> getAuthors() {
        return authorRepository.findAll().stream()
                .map(a -> modelMapper.map(a, AuthorDto.class))
                .collect(Collectors.toList());
    }

    public AuthorDto createAuthor(CreateAuthorCommand command) {
        Author author = new Author(command.getName());
        authorRepository.save(author);
        return modelMapper.map(author, AuthorDto.class);
    }

    @Transactional
    public AuthorDto addBook(long id, AddBookCommand command) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author with id: " + id + " not found."));
        author.addBook(new Book(command.getIsbnNumber(), command.getTitle()));
        return modelMapper.map(author, AuthorDto.class);
    }

    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }
}
