package rs.jspasic.league.service;

import rs.jspasic.league.model.Group;

public interface GroupService {

    Group findGroupById(Long groupId);
    Group findByLeagueNameAndGroupName(String leagueName, String groupName);

}
