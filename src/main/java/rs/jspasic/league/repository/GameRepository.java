package rs.jspasic.league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.jspasic.league.model.Game;
import rs.jspasic.league.model.Group;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select game from Game game inner join game.group")
    List<Game> findByGroup(Group g);
}
