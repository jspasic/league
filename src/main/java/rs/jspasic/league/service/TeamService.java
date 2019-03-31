package rs.jspasic.league.service;

import rs.jspasic.league.model.Team;

public interface TeamService {

    Team findTeamById(Long teamId);
    Team findTeamByName(String teamName);

}
