package rs.jspasic.league.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rs.jspasic.league.model.Group;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    @Transactional
    public void findGroupByIdFails() {
        Long groupId = 123L;
        Optional<Group> go = groupRepository.findById(groupId);
        assertFalse(go.isPresent());
    }

    @Test
    @Transactional
    public void findGroupByIdSucceeds() {
        Long groupId = 1001L;
        Optional<Group> go = groupRepository.findById(groupId);
        assertTrue(go.isPresent());
    }

    @Test
    @Transactional
    public void groupHasCorrectNumberOfTeams() {
        Long groupId = 1002L;
        Optional<Group> go = groupRepository.findById(groupId);
        assertTrue(go.isPresent());
        go.ifPresent(g -> assertEquals(4, g.getTeams().size()));
    }

    @Test
    @Transactional
    public void groupHasCorrectNumberOfGames() {
        Long groupId = 1002L;
        Optional<Group> go = groupRepository.findById(groupId);
        assertTrue(go.isPresent());
        go.ifPresent(g -> assertEquals(12, g.getGames().size()));
    }

    @Test
    @Transactional
    public void findGroupByLeagueNameAndGroupNameSucceeds() {
        String leagueName = "Champions league 2017/18";
        String groupName = "C";
        Optional<Group> go = groupRepository.findByLeagueNameAndGroupName(leagueName, groupName);
        assertTrue(go.isPresent());
        go.ifPresent(g -> assertEquals(1003L, g.getId().longValue()));
    }

    @Test
    @Transactional
    public void findByLeagueNameSucceeds() {
        String leagueName = "Champions league 2017/18";
        List<Group> groups = groupRepository.findByLeagueName(leagueName);
        assertEquals(8, groups.size());
    }
}