package rs.jspasic.league.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.jspasic.league.model.Game;
import rs.jspasic.league.model.Group;
import rs.jspasic.league.model.GroupStandings;
import rs.jspasic.league.model.League;
import rs.jspasic.league.model.Team;
import rs.jspasic.league.service.GameService;
import rs.jspasic.league.service.GroupService;
import rs.jspasic.league.service.LeagueService;
import rs.jspasic.league.service.TeamService;
import rs.jspasic.league.util.StandingsCalculator;
import rs.jspasic.league.web.model.GameWrapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/results")
public class ResultsController {

    private final GameService gameService;
    private final TeamService teamService;
    private final GroupService groupService;
    private final LeagueService leagueService;

    @Autowired
    public ResultsController(GameService gameService, TeamService teamService, GroupService groupService, LeagueService leagueService) {
        this.gameService = gameService;
        this.teamService = teamService;
        this.groupService = groupService;
        this.leagueService = leagueService;
    }

    @PostMapping(path = "/group")
    public List<GroupStandings> saveGames(@RequestBody List<GameWrapper> gameResults) {
        List<Game> games = gameResults.stream()
                .map(gw -> extractAndHydrateGame(gw))
                .collect(Collectors.toList());

        games = gameService.saveAllGames(games);

        Set<Group> groups = games.stream()
                .map(Game::getGroup)
                .collect(Collectors.toSet());

        List<GroupStandings> allGroupsStandings = groups.stream()
                .map(StandingsCalculator::getGroupStandings)
                .collect(Collectors.toList());

        return allGroupsStandings;
    }

    private Game extractAndHydrateGame(GameWrapper gameWrapper) {
        League league = leagueService.findLeagueByName(gameWrapper.getLeagueTitle());
        Group group = groupService.findByLeagueNameAndGroupName(gameWrapper.getLeagueTitle(), gameWrapper.getGroup());
        Team homeTeam = teamService.findTeamByName(gameWrapper.getHomeTeam());
        Team awayTeam = teamService.findTeamByName(gameWrapper.getAwayTeam());

        Game game = gameWrapper.getGame();
        game.setLeague(league);
        game.setGroup(group);
        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);

        String[] score = gameWrapper.getScore().split(":", 2);
        int homeTeamGoals = Integer.parseInt(score[0]);
        int awayTeamGoals = Integer.parseInt(score[1]);
        game.setHomeTeamGoals(homeTeamGoals);
        game.setAwayTeamGoals(awayTeamGoals);

        return game;
    }
}
