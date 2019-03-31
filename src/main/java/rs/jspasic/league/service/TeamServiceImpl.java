package rs.jspasic.league.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.jspasic.league.model.Team;
import rs.jspasic.league.repository.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team findTeamById(Long teamId) {
        return null;
    }

    @Override
    public Team findTeamByName(String teamName) {
        return null;
    }
}
