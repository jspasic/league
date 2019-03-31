package rs.jspasic.league.service;

import rs.jspasic.league.model.Group;
import rs.jspasic.league.model.League;

import java.util.List;

public interface GroupService {

    List<Group> findGroupsForLeague(League league);
    List<Group> findGroupsForLeague(String leagueName);

}
