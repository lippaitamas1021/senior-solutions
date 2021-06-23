package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class GameServiceTest {

    GameService gameService;

    @BeforeEach
    void init() {
        GameRepository gameRepository = new GameRepository();
        gameRepository.addGame(new Game("Hungary", "Portugal", "1", "0"));
        gameRepository.addGame(new Game("Denmark", "Finnland", "0", "1"));
        gameRepository.addGame(new Game("Wales", "Switzerland", "1", "1"));
        gameRepository.addGame(new Game("Turkey", "Wales", "1", "1"));
        gameRepository.addGame(new Game("Turkey", "Italy", "0", "3"));
        gameRepository.addGame(new Game("Spain", "Sweden", "0", "0"));
        gameService = new GameService(gameRepository);
    }

    @Test
    void biggestDifferenceTest() {
        Game expected = gameService.biggestDifference();
        Assertions.assertEquals("Turkey", expected.getFirstCountry());
        Assertions.assertEquals("Italy", expected.getSecondCountry());
        Assertions.assertEquals(0, expected.getFirstCountryScore());
        Assertions.assertEquals(3, expected.getSecondCountryScore());
    }

    @ParameterizedTest
    @MethodSource("createArguments")
    void getGoalsTest(String country, int goals) {
        Assertions.assertEquals(goals, gameService.getGoals(country));
    }

    static Stream<Arguments> createArguments() {
        return Stream.of(
                arguments("Hungary", 1),
                arguments("Denmark", 0),
                arguments("Turkey", 1),
                arguments("Wales", 2)
        );
    }

    @Test
    void countryWithMostGoalsTest() {
        Assertions.assertEquals("Wales", gameService.getCountryWithMostGoals());
        Assertions.assertEquals("Italy", gameService.getCountryWithMostGoals2());
        Assertions.assertEquals("Italy", gameService.mostGoalsCountry());
    }
}
