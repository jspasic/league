package rs.jspasic.league.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.jspasic.league.model.Game;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void findGameByIdFails() {
        Long gameId = 123L;
        Optional<Game> go = gameRepository.findById(gameId);
        assertFalse(go.isPresent());
    }

    @Test
    public void findGameByIdSucceeds() {
        Long gameId = 1001L;
        Optional<Game> go = gameRepository.findById(gameId);
        assertTrue(go.isPresent());
    }

}