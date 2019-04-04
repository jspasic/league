package rs.jspasic.league.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.jspasic.league.exception.GroupNotFoundException;
import rs.jspasic.league.exception.LeagueNotFoundException;
import rs.jspasic.league.exception.TeamNotFoundException;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        validateSaveGameRequest(gameResults);


        String leagueName = gameResults.stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid input"))
                .getLeagueTitle();

        List<Game> games = gameResults.stream()
                .map(this::extractAndHydrateGame)
                .collect(Collectors.toList());

        gameService.saveAllGames(games);

        List<Group> groups = groupService.findByLeagueName(leagueName);
        for (Group group: groups) {
            List<Game> groupGames = gameService.findByGroup(group);
            group.setGames(groupGames);
        }


        return groupService.getGroupsStandings(groups);
    }

    @GetMapping("/{leagueId}")
    public List<GroupStandings> getCurrentStandings(@PathVariable Long leagueId, @RequestParam(name = "groupName", required = false) List<String> requestedGroups) {
        List<Group> groups;
        if (requestedGroups == null || requestedGroups.isEmpty()) {
            groups = groupService.findByLeagueId(leagueId);
        } else {
            groups = groupService.findByLeagueIdAndGroupNames(leagueId, requestedGroups);
        }

        return groups.stream()
                .map(StandingsCalculator::getGroupStandings)
                .collect(Collectors.toList());
    }

    // makes sure all data is in place for recording game results
    private void validateSaveGameRequest(List<GameWrapper> gameResults) {
        if (gameResults.isEmpty()) {
            throw new RuntimeException("Results list cannot be empty");
        }

        Set<String> leagueNames = new HashSet<>();
        Set<String> groupNames = new HashSet<>();
        Set<String> teamNames = new HashSet<>();
        Map<String, Set<String>> groupsTeams = new HashMap<>();
        for (GameWrapper result: gameResults) {
            leagueNames.add(result.getLeagueTitle());
            groupNames.add(result.getGroup());
            teamNames.add(result.getHomeTeam());
            teamNames.add(result.getAwayTeam());

            if (!groupsTeams.containsKey(result.getGroup())) {
                Set<String> val = new HashSet<>();
                groupsTeams.put(result.getGroup(), val);
            }
            groupsTeams.get(result.getGroup()).add(result.getHomeTeam());
            groupsTeams.get(result.getGroup()).add(result.getAwayTeam());
        }

        if (leagueNames.size() != 1) {
            throw new RuntimeException("Results can be added for one league only in one request");
        }

        List<Team> teams = new ArrayList<>();
        for (String tname: teamNames) {
            try {
                Team t = teamService.findTeamByName(tname);
                teams.add(t);
            } catch (TeamNotFoundException e) {
                Team t = new Team(tname);
                t = teamService.addTeam(t);
                teams.add(t);
            }
        }

        String leagueName = leagueNames.stream().findFirst().get();
        League league;
        try {
            league = leagueService.findLeagueByName(leagueName);
        } catch (LeagueNotFoundException e) {
            league = new League(leagueName);
            league.setTeams(teams);
            league = leagueService.addLeague(league);
        }

        List<Group> groups = new ArrayList<>();
        for (String gname: groupNames) {
            try {
                Group g = groupService.findByLeagueNameAndGroupName(leagueName, gname);
                groups.add(g);
            } catch (GroupNotFoundException e) {
                Group g = new Group(league, gname);

                Set<String> tnames = groupsTeams.get(gname);
                List<Team> groupTeams = tnames.stream()
                        .map(teamService::findTeamByName)
                        .collect(Collectors.toList());
                g.setTeams(groupTeams);

                groupService.addGroup(g);
            }
        }
    }

    private Game extractAndHydrateGame(GameWrapper gameWrapper) {
        League league = leagueService.findLeagueByName(gameWrapper.getLeagueTitle());
        Group group = groupService.findByLeagueNameAndGroupName(gameWrapper.getLeagueTitle(), gameWrapper.getGroup());
        Team homeTeam = teamService.findTeamByName(gameWrapper.getHomeTeam());
        Team awayTeam = teamService.findTeamByName(gameWrapper.getAwayTeam());

        return populateGame(gameWrapper, league, group, homeTeam, awayTeam);
    }

    private Game populateGame(GameWrapper gameWrapper, League league, Group group, Team homeTeam, Team awayTeam) {
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
