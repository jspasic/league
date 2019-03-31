package rs.jspasic.league.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.jspasic.league.model.Game;
import rs.jspasic.league.service.GameServiceImpl;
import rs.jspasic.league.service.GroupServiceImpl;
import rs.jspasic.league.service.LeagueServiceImpl;
import rs.jspasic.league.service.TeamServiceImpl;
import rs.jspasic.league.web.model.GameWrapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultsController {

    private final GameServiceImpl gameService;
    private final TeamServiceImpl teamService;
    private final GroupServiceImpl groupService;
    private final LeagueServiceImpl leagueService;

    @Autowired
    public ResultsController(GameServiceImpl gameService, TeamServiceImpl teamService, GroupServiceImpl groupService, LeagueServiceImpl leagueService) {
        this.gameService = gameService;
        this.teamService = teamService;
        this.groupService = groupService;
        this.leagueService = leagueService;
    }

    @PostMapping(path = "/group")
    public List<Game> saveGames(@RequestBody List<GameWrapper> gameResults) {

        return new ArrayList<>();
    }
}
