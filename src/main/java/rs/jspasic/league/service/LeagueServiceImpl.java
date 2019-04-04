package rs.jspasic.league.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.jspasic.league.exception.LeagueNotFoundException;
import rs.jspasic.league.model.Game;
import rs.jspasic.league.model.League;
import rs.jspasic.league.repository.LeagueRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public League findLeagueById(Long leagueId) {
        Optional<League> lo = leagueRepository.findById(leagueId);
        return lo.orElseThrow(() -> new LeagueNotFoundException("No League with id=" + leagueId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public League findLeagueByName(String leagueName) {
        Optional<League> lo = leagueRepository.findByLeagueName(leagueName);
        return lo.orElseThrow(() -> new LeagueNotFoundException("No League with name=" + leagueName));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<League> findAll() {
        return leagueRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public League addLeague(League league) {
        return leagueRepository.save(league);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public League updateLeague(League league) {
        return leagueRepository.save(league);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public League addGameToLeague(League league, Game game) {
        if (!league.getGames().contains(game)) {
            league.getGames().add(game);
            return leagueRepository.save(league);
        }
        return league;
    }
}
