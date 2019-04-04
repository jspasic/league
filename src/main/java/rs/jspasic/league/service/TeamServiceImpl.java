package rs.jspasic.league.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.jspasic.league.exception.TeamNotFoundException;
import rs.jspasic.league.model.Team;
import rs.jspasic.league.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Team findTeamById(Long teamId) {
        Optional<Team> to = teamRepository.findById(teamId);
        return to.orElseThrow(TeamNotFoundException::new);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Team findTeamByName(String teamName) {
        Optional<Team> to = teamRepository.findByTeamName(teamName);
        return to.orElseThrow(TeamNotFoundException::new);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Team> addTeams(List<Team> teams) {
        return teamRepository.saveAll(teams);
    }


}
