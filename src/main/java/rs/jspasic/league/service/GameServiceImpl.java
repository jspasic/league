package rs.jspasic.league.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.jspasic.league.exception.GameNotFoundException;
import rs.jspasic.league.model.Game;
import rs.jspasic.league.model.Group;
import rs.jspasic.league.repository.GameRepository;

import java.util.Date;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Game> saveAllGames(List<Game> games) {
        return gameRepository.saveAll(games);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Game> findByGroup(Group g) {
        return gameRepository.findByGroup(g);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Game> findGames(Long leagueId, Date dateFrom, Date dateTo, String groupName, String teamName) {
        return gameRepository.findGames(leagueId, dateFrom, dateTo, groupName, teamName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Game findById(Long gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException("No game with gameId=" + gameId));
    }
}
