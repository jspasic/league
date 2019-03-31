package rs.jspasic.league.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.jspasic.league.model.League;
import rs.jspasic.league.repository.LeagueRepository;

import java.util.Optional;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public League findLeagueById(Long leagueId) {
        Optional<League> lo = leagueRepository.findById(leagueId);
        return lo.orElseThrow(() -> new RuntimeException("No League with id=" + leagueId));
    }

    @Override
    public League findLeagueByName(String leagueName) {
        Optional<League> lo = leagueRepository.findByLeagueName(leagueName);
        return lo.orElseThrow(() -> new RuntimeException("No League with name=" + leagueName));
    }
}