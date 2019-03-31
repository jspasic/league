package rs.jspasic.league.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.jspasic.league.model.Game;
import rs.jspasic.league.model.Group;
import rs.jspasic.league.model.League;
import rs.jspasic.league.repository.GameRepository;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> findAllGamesForLeague(League league) {
        return null;
    }

    @Override
    public List<Game> findAllGamesForLeague(String leagueName) {
        return null;
    }

    @Override
    public List<Game> findAllGamesForGroup(Group group) {
        return null;
    }

    @Override
    public List<Game> findAllGamesByLeagueNameAndGroupName(String leagueName, String groupName) {
        return null;
    }
}
