package rs.jspasic.league.service;

import rs.jspasic.league.model.Group;

import java.util.List;

public interface GroupService {

    Group findGroupById(Long groupId);
    Group findByLeagueNameAndGroupName(String leagueName, String groupName);
    List<Group> findByLeagueIdAndGroupNames(Long leagueId, List<String> groupNames);
    List<Group> findByLeagueId(Long leagueId);
    Group addGroup(Group group);
    List<Group> addGroups(List<Group> groups);
}
