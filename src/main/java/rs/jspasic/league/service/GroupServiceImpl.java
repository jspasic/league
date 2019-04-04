package rs.jspasic.league.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.jspasic.league.model.Group;
import rs.jspasic.league.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Group findGroupById(Long groupId) {
        Optional<Group> go = groupRepository.findById(groupId);
        return go.orElseThrow(() -> new RuntimeException("No group with id=" + groupId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Group findByLeagueNameAndGroupName(String leagueName, String groupName) {
        Optional<Group> go = groupRepository.findByLeagueNameAndGroupName(leagueName, groupName);
        return go.orElseThrow(() -> new RuntimeException("No group found for leagueName=" + leagueName + " and groupName=" + groupName));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> findByLeagueIdAndGroupNames(Long leagueId, List<String> groupNames) {
        return groupRepository.findByLeagueIdAndGroupNames(leagueId, groupNames);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> findByLeagueId(Long leagueId) {
        return groupRepository.findByLeagueId(leagueId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Group> addGroups(List<Group> groups) {
        return groupRepository.saveAll(groups);
    }
}
