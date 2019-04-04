package rs.jspasic.league.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.jspasic.league.model.Game;
import rs.jspasic.league.service.GameService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/{leagueId}")
    public List<Game> getLeagueGames(@PathVariable Long leagueId,
                                     @RequestParam(required = false) Date dateFrom,
                                     @RequestParam(required = false) Date dateTo,
                                     @RequestParam(required = false) String groupName,
                                     @RequestParam(required = false) String teamName) {

        return gameService.findGames(leagueId, dateFrom, dateTo, groupName, teamName);
    }

    @PutMapping("/{gameId}")
    public Game updateGame(@PathVariable Long gameId, @RequestBody Game game) {
        if (game.getId() == null) {
            throw new RuntimeException("Game must have an id");
        }
        gameService.findById(game.getId()); // to make sure the game exists; an exception will be thrown if it doesn't
        return gameService.saveGame(game);
    }

    @PutMapping("/updateMultiple")
    public List<Game> updateGames(@RequestBody List<Game> games) {
        for (Game game: games) {
            if (game.getId() == null) {
                throw new RuntimeException("All games must have an id");
            }
            gameService.findById(game.getId()); // to make sure the game exists; an exception will be thrown if it doesn't
        }
        return gameService.saveAllGames(games);
    }

    @PostMapping
    public Game addGame(@RequestBody Game game) {
        return gameService.saveGame(game);
    }

    @PostMapping("/addMultiple")
    public List<Game> addGame(@RequestBody List<Game> games) {
        return gameService.saveAllGames(games);
    }
}
