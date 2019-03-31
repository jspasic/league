package rs.jspasic.league.service;

import rs.jspasic.league.model.Game;
import rs.jspasic.league.model.Group;
import rs.jspasic.league.model.League;

import java.util.List;

public interface GameService {
    List<Game> findAllGamesForLeague(League league);
    List<Game> findAllGamesForLeague(String leagueName);
    List<Game> findAllGamesForGroup(Group group);
    List<Game> findAllGamesByLeagueNameAndGroupName(String leagueName, String groupName);
}
