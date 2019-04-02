package rs.jspasic.league.service;

import rs.jspasic.league.model.Game;

import java.util.List;

public interface GameService {
    List<Game> saveAllGames(List<Game> games);
}
