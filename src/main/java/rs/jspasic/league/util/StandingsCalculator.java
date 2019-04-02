package rs.jspasic.league.util;

import rs.jspasic.league.model.Game;
import rs.jspasic.league.model.Group;
import rs.jspasic.league.model.GroupStandings;
import rs.jspasic.league.model.Team;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StandingsCalculator {

    public static GroupStandings getGroupStandings(Group g) {
        List<Team> teams = g.getTeams();
        List<Game> games = g.getGames();

        Map<Long, StandingsEntry> standingsMap = new HashMap<>();
        teams.forEach(t -> standingsMap.put(t.getId(), new StandingsEntry(t.getTeamName())));

        int matchday = 0;
        for (Game game: games) {
            if (game.getMatchday() > matchday) {
                matchday = game.getMatchday();
            }

            Team homeTeam = game.getHomeTeam();
            Team awayTeam = game.getAwayTeam();

            StandingsEntry htse = standingsMap.get(homeTeam.getId());
            htse.incrementPlayedGames();
            htse.incrementGoalsForAndUpdateDifference(game.getHomeTeamGoals());
            htse.incrementGoalsAgainstAndUpdateDifference(game.getAwayTeamGoals());

            StandingsEntry atse = standingsMap.get(awayTeam.getId());
            atse.incrementPlayedGames();
            atse.incrementGoalsForAndUpdateDifference(game.getAwayTeamGoals());
            atse.incrementGoalsAgainstAndUpdateDifference(game.getHomeTeamGoals());

            if (game.isDraw()) {
                htse.addDrawAndUpdatePoints();
                atse.addDrawAndUpdatePoints();
            } else if (game.isHomeTeamWin()) {
                htse.addWinAndUpdatePoints();
                atse.addLoss();
            } else {
                htse.addLoss();
                atse.addWinAndUpdatePoints();
            }
        }


        List<StandingsEntry> standings = teams.stream()
                .map(t -> t.getId())
                .map(id -> standingsMap.get(id))
                .collect(Collectors.toList());

        StandingsEntryComparator comparator = new StandingsEntryComparator();
        Collections.sort(standings, comparator);

        for (int i = 0; i < standings.size(); i++) {
            standings.get(i).setRank(i+1);
        }

        GroupStandings groupStandings = new GroupStandings();
        groupStandings.setGroupName(g.getGroupName());
        groupStandings.setLeagueTitle(g.getLeague().getLeagueName());
        groupStandings.setMatchday(matchday);
        groupStandings.setStandings(standings);

        return groupStandings;
    }

}
