package rs.jspasic.league.repository;

import org.springframework.beans.factory.annotation.Autowired;
import rs.jspasic.league.model.Game;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class CustomGameRepositoryImpl implements CustomGameRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Game> findGames(Long leagueId, Date dateFrom, Date dateTo, String groupName, String teamName) {
        StringBuilder sb = new StringBuilder("select game from Game game inner join game.league where game.league.id = :leagueId");

        // inner join game.group inner join game.homeTeam inner join game.awayTeam
        if (dateFrom != null) {
            sb.append(" and game.kickoffAt > :dateFrom");
        }
        if (dateTo != null) {
            sb.append(" and game.kickoffAt < :dateTo");
        }
        if (groupName != null) {
            sb.append(" and game.group.groupName = :groupName");
        }
        if (teamName != null) {
            sb.append(" and (game.homeTeam.teamName = :teamName or game.awayTeam.teamName = :teamName)");
        }
        String queryString = sb.toString();


        Query query = entityManager.createQuery(queryString, Game.class);
        query.setParameter("leagueId", leagueId);
        if (dateFrom != null) {
            query.setParameter("dateFrom", dateFrom);
        }
        if (dateTo != null) {
            query.setParameter("dateTo", dateTo);
        }
        if (groupName != null) {
            query.setParameter("groupName", groupName);
        }
        if (teamName != null) {
            query.setParameter("teamName", teamName);
        }

        List<Game> games = query.getResultList();
        return games;
    }
}
