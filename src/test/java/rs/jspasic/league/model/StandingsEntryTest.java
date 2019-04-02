package rs.jspasic.league.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StandingsEntryTest {

    @Test
    public void testAddWin() {
        StandingsEntry entry = new StandingsEntry("Team");
        assertEquals(0, entry.getPoints());
        assertEquals(0, entry.getWin());

        entry.addWinAndUpdatePoints();
        assertEquals(3, entry.getPoints());
        assertEquals(1, entry.getWin());
    }

    @Test
    public void testAddDraw() {
        StandingsEntry entry = new StandingsEntry("Team");
        assertEquals(0, entry.getPoints());
        assertEquals(0, entry.getDraw());

        entry.addDrawAndUpdatePoints();
        assertEquals(1, entry.getPoints());
        assertEquals(1, entry.getDraw());
    }

    @Test
    public void testAddLoss() {
        StandingsEntry entry = new StandingsEntry("Team");
        assertEquals(0, entry.getPoints());
        assertEquals(0, entry.getLose());

        entry.addLoss();
        assertEquals(0, entry.getPoints());
        assertEquals(1, entry.getLose());
    }

    @Test
    public void testIncrementGoalsForAndUpdateDifference() {
        StandingsEntry entry = new StandingsEntry("Team");
        assertEquals(0, entry.getGoals());
        assertEquals(0, entry.getGoalDifference());

        entry.incrementGoalsForAndUpdateDifference(3);
        assertEquals(3, entry.getGoals());
        assertEquals(3, entry.getGoalDifference());
    }

    @Test
    public void testIncrementGoalsAgainstAndUpdateDifference() {
        StandingsEntry entry = new StandingsEntry("Team");
        assertEquals(0, entry.getGoalsAgainst());
        assertEquals(0, entry.getGoalDifference());

        entry.incrementGoalsAgainstAndUpdateDifference(3);
        assertEquals(3, entry.getGoalsAgainst());
        assertEquals(-3, entry.getGoalDifference());    }
}