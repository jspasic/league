package rs.jspasic.league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.jspasic.league.model.Group;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("select g from Group g inner join g.league where g.league.leagueName = :leagueName and g.groupName = :groupName")
    Optional<Group> findByLeagueNameAndGroupName(String leagueName, String groupName);
}
