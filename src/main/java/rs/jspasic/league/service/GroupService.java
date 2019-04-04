package rs.jspasic.league.service;

import rs.jspasic.league.model.Game;
import rs.jspasic.league.model.Group;
import rs.jspasic.league.model.GroupStandings;

import java.util.List;

public interface GroupService {

    Group findGroupById(Long groupId);
    Group findByLeagueNameAndGroupName(String leagueName, String groupName);
    List<Group> findByLeagueIdAndGroupNames(Long leagueId, List<String> groupNames);
    List<Group> findByLeagueId(Long leagueId);
    List<Group> findByLeagueName(String leagueName);
    Group addGroup(Group group);
    List<Group> addGroups(List<Group> groups);
    Group updateGroup(Group group);
    Group addGameToGroup(Group group, Game game);
    GroupStandings getGroupStandings(Group group);
    List<GroupStandings> getGroupsStandings(List<Group> groups);
}
