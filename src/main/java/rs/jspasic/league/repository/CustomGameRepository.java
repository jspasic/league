package rs.jspasic.league.repository;

import rs.jspasic.league.model.Game;

import java.util.Date;
import java.util.List;

public interface CustomGameRepository {
    List<Game> findGames(Long leagueId, Date dateFrom, Date dateTo, String groupName, String teamName);
}
