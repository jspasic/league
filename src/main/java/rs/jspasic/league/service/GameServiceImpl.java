package rs.jspasic.league.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.jspasic.league.model.Game;
import rs.jspasic.league.repository.GameRepository;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Game> saveAllGames(List<Game> games) {
        return gameRepository.saveAll(games);
    }
}
