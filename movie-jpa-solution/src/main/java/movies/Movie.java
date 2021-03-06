package movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ElementCollection
    private List<Integer> ratings;

    public Movie(String title) {
        this.title = title;
    }

    public void addRating(int rating) {
        if (ratings == null) {
            ratings = new ArrayList<>();
        }
        ratings.add(rating);
    }
}
