package rs.jspasic.league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.jspasic.league.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
