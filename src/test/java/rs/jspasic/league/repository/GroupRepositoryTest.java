package rs.jspasic.league.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.jspasic.league.model.Group;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void findGroupByIdFails() {
        Long groupId = 123L;
        Optional<Group> go = groupRepository.findById(groupId);
        assertFalse(go.isPresent());
    }

    @Test
    public void findGroupByIdSucceeds() {
        Long groupId = 1001L;
        Optional<Group> go = groupRepository.findById(groupId);
        assertTrue(go.isPresent());
    }
}