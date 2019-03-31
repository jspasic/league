package rs.jspasic.league.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.jspasic.league.model.Team;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void findTeamByNameFails() {
        String teamName = "Radnicki";
        Optional<Team> to = teamRepository.findByTeamName(teamName);
        assertFalse(to.isPresent());
    }

    @Test
    public void findTeamByNameSucceeds() {
        String teamName = "Arsenal";
        Optional<Team> to = teamRepository.findByTeamName(teamName);
        assertTrue(to.isPresent());
    }
}