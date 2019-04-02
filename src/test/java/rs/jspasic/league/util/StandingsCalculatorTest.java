package rs.jspasic.league.util;

import org.junit.Test;
import rs.jspasic.league.model.Game;
import rs.jspasic.league.model.Group;
import rs.jspasic.league.model.GroupStandings;
import rs.jspasic.league.model.League;
import rs.jspasic.league.model.Team;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StandingsCalculatorTest {

    @Test
    public void test() {
        League league = new League();
        league.setLeagueName("Champions League 2017/18");

        Group group = new Group();
        group.setId(1001L);
        group.setGroupName("A");
        group.setLeague(league);

        Team t1 = new Team(1L, "Arsenal");
        Team t2 = new Team(2L, "PSG");
        Team t3 = new Team(3L, "Basel");
        Team t4 = new Team(4L, "Ludogorets");
        List<Team> teams = Arrays.asList(t1, t2, t3, t4);

        Game g01 = new Game(1L, league, 1, group, t2, t1, new Date(), 1, 1);
        Game g02 = new Game(2L, league, 1, group, t3, t4, new Date(), 1, 1);
        Game g03 = new Game(3L, league, 2, group, t1, t3, new Date(), 2, 0);
        Game g04 = new Game(4L, league, 2, group, t4, t2, new Date(), 1, 3);
        Game g05 = new Game(5L, league, 3, group, t1, t4, new Date(), 6, 0);
        Game g06 = new Game(6L, league, 3, group, t2, t3, new Date(), 3, 0);
        Game g07 = new Game(7L, league, 4, group, t4, t1, new Date(), 2, 3);
        Game g08 = new Game(8L, league, 4, group, t3, t2, new Date(), 1, 2);
        Game g09 = new Game(9L, league, 5, group, t1, t2, new Date(), 2, 2);
        Game g10 = new Game(10L, league, 5, group, t4, t3, new Date(), 0, 0);
        Game g11 = new Game(11L, league, 6, group, t3, t1, new Date(), 1, 4);
        Game g12 = new Game(12L, league, 6, group, t2, t4, new Date(), 2, 2);
        List<Game> games = Arrays.asList(g01, g02, g03, g04, g05, g06, g07, g08, g09, g10, g11, g12);


        group.setTeams(teams);
        group.setGames(games);

        GroupStandings groupStandings = StandingsCalculator.getGroupStandings(group);

        assertNotNull(groupStandings);
        assertEquals(4, groupStandings.getStandings().size());

        assertEquals(1, groupStandings.getStandings().get(0).getRank());
        assertEquals("Arsenal", groupStandings.getStandings().get(0).getTeam());
        assertEquals(14, groupStandings.getStandings().get(0).getPoints());
        assertEquals(18, groupStandings.getStandings().get(0).getGoals());
        assertEquals(6, groupStandings.getStandings().get(0).getGoalsAgainst());
        assertEquals(12, groupStandings.getStandings().get(0).getGoalDifference());

        assertEquals(2, groupStandings.getStandings().get(1).getRank());
        assertEquals("PSG", groupStandings.getStandings().get(1).getTeam());
        assertEquals(12, groupStandings.getStandings().get(1).getPoints());
        assertEquals(13, groupStandings.getStandings().get(1).getGoals());
        assertEquals(7, groupStandings.getStandings().get(1).getGoalsAgainst());
        assertEquals(6, groupStandings.getStandings().get(1).getGoalDifference());

        assertEquals(3, groupStandings.getStandings().get(2).getRank());
        assertEquals("Ludogorets", groupStandings.getStandings().get(2).getTeam());
        assertEquals(3, groupStandings.getStandings().get(2).getPoints());
        assertEquals(6, groupStandings.getStandings().get(2).getGoals());
        assertEquals(15, groupStandings.getStandings().get(2).getGoalsAgainst());
        assertEquals(-9, groupStandings.getStandings().get(2).getGoalDifference());

        assertEquals(4, groupStandings.getStandings().get(3).getRank());
        assertEquals("Basel", groupStandings.getStandings().get(3).getTeam());
        assertEquals(2, groupStandings.getStandings().get(3).getPoints());
        assertEquals(3, groupStandings.getStandings().get(3).getGoals());
        assertEquals(12, groupStandings.getStandings().get(3).getGoalsAgainst());
        assertEquals(-9, groupStandings.getStandings().get(3).getGoalDifference());
    }
}