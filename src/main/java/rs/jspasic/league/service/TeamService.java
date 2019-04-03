package rs.jspasic.league.service;

import rs.jspasic.league.model.Team;

import java.util.List;

public interface TeamService {

    Team findTeamById(Long teamId);
    Team findTeamByName(String teamName);
    List<Team> findAll();
    Team addTeam(Team team);
    List<Team> addTeams(List<Team> teams);
}
