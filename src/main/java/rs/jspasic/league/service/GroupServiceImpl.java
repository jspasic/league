package rs.jspasic.league.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.jspasic.league.model.Group;
import rs.jspasic.league.model.League;
import rs.jspasic.league.repository.GroupRepository;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;


    @Override
    public List<Group> findGroupsForLeague(League league) {
        return null;
    }

    @Override
    public List<Group> findGroupsForLeague(String leagueName) {
        return null;
    }
}
