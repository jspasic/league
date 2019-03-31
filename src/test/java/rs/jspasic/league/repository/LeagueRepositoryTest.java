package rs.jspasic.league.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rs.jspasic.league.model.League;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LeagueRepositoryTest {

    @Autowired
    private LeagueRepository leagueRepository;

    @Test
    @Transactional
    public void findLeagueRepositoryByIdFails() {
        Long leagueId = 23L;
        Optional<League> lo = leagueRepository.findById(leagueId);
        assertFalse(lo.isPresent());
    }

    @Test
    @Transactional
    public void findLeagueRepositoryByIdSucceeds() {
        Long leagueId = 1001L;
        Optional<League> lo = leagueRepository.findById(leagueId);
        assertTrue(lo.isPresent());
    }

    @Test
    @Transactional
    public void findLeagueRepositoryByNameThrowsException() {
        String leagueName = "Champions league";
        Optional<League> lo = leagueRepository.findByLeagueName(leagueName);
        assertFalse(lo.isPresent());
    }

    @Test
    @Transactional
    public void findLeagueRepositoryByNameSucceeds() {
        String leagueName = "Champions league 2020/21";
        Optional<League> lo = leagueRepository.findByLeagueName(leagueName);
        assertTrue(lo.isPresent());
    }
}