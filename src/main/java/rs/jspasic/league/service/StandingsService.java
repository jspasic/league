package rs.jspasic.league.service;

import rs.jspasic.league.model.Group;
import rs.jspasic.league.model.GroupStandings;

public interface StandingsService {
    GroupStandings getGroupStandings(Group g);
}
