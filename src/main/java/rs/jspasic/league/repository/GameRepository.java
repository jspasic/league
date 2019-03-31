package rs.jspasic.league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.jspasic.league.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
