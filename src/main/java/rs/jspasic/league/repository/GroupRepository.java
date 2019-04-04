package rs.jspasic.league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.jspasic.league.model.Group;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("select g from Group g inner join g.league where g.league.leagueName = :leagueName and g.groupName = :groupName")
    Optional<Group> findByLeagueNameAndGroupName(String leagueName, String groupName);

    @Query("select g from Group g inner join g.league inner join g.games inner join g.teams where g.league.leagueId = :leagueId and g.groupName in (:groupNames)")
    List<Group> findByLeagueIdAndGroupNames(Long leagueId, List<String> groupNames);

    @Query("select g from Group g inner join g.league where g.league.id = :leagueId")
    List<Group> findByLeagueId(Long leagueId);
}
