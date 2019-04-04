package rs.jspasic.league.service;

import rs.jspasic.league.model.Game;
import rs.jspasic.league.model.Group;

import java.util.List;

public interface GameService {
    Game saveGame(Game game);
    List<Game> saveAllGames(List<Game> games);
    List<Game> findByGroup(Group g);
}
