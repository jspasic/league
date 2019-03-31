package rs.jspasic.league.service;

import rs.jspasic.league.model.League;

public interface LeagueService {
    League findLeagueById(Long leagueId);
    League findLeagueByName(String leagueName);
}
