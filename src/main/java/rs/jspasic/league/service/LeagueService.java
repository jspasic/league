package rs.jspasic.league.service;

import rs.jspasic.league.model.Game;
import rs.jspasic.league.model.League;

import java.util.List;

public interface LeagueService {
    League findLeagueById(Long leagueId);
    League findLeagueByName(String leagueName);
    List<League> findAll();
    League addLeague(League league);
    League updateLeague(League league);
    League addGameToLeague(League league, Game game);
}
