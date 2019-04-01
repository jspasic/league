package rs.jspasic.league.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rs.jspasic.league.model.Game;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    @Transactional
    public void findGameByIdFails() {
        Long gameId = 123L;
        Optional<Game> go = gameRepository.findById(gameId);
        assertFalse(go.isPresent());
    }

    @Test
    @Transactional
    public void findGameByIdSucceeds() {
        Long gameId = 1001L;
        Optional<Game> go = gameRepository.findById(gameId);
        assertTrue(go.isPresent());
    }

    @Test
    @Transactional
    public void gameDataCorrect() {
        Long gameId = 1017L;
        Optional<Game> go = gameRepository.findById(gameId);
        assertTrue(go.isPresent());
        go.ifPresent(g -> assertEquals("B", g.getGroup().getGroupName()));
        go.ifPresent(g -> assertEquals("Anderlecht", g.getHomeTeam().getTeamName()));
        go.ifPresent(g -> assertEquals("Paris Saint-Germain", g.getAwayTeam().getTeamName()));
        go.ifPresent(g -> assertEquals(0, g.getHomeTeamGoals().intValue()));
        go.ifPresent(g -> assertEquals(4, g.getAwayTeamGoals().intValue()));
        go.ifPresent(g -> assertEquals(3, g.getMatchday().intValue()));
    }
}