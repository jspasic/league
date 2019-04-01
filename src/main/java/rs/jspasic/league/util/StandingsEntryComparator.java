package rs.jspasic.league.util;

import rs.jspasic.league.model.StandingsEntry;

import java.util.Comparator;

public class StandingsEntryComparator implements Comparator<StandingsEntry> {
    @Override
    public int compare(StandingsEntry se1, StandingsEntry se2) {
        if (se1.getPoints() > se2.getPoints()) {
            return 1;
        } else if (se1.getPoints() < se2.getPoints()) {
            return -1;
        } else {
            if (se1.getGoals() > se2.getGoals()) {
                return 1;
            } else if (se1.getGoals() < se2.getGoals()) {
                return -1;
            } else {
                if (se1.getGoalDifference() > se2.getGoalDifference()) {
                    return 1;
                } else if (se1.getGoalDifference() > se2.getGoalDifference()) {
                    return -1;
                } else return 0;
            }
        }

    }
}
