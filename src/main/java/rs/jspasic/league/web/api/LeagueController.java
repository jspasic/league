package rs.jspasic.league.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.jspasic.league.model.League;
import rs.jspasic.league.service.LeagueService;

import java.util.List;

@RestController
@RequestMapping("/api/leagues")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @GetMapping
    public List<League> getLeagues() {
        return leagueService.findAll();
    }

    @PostMapping
    public League addLeague(@RequestBody League league) {
        return leagueService.addLeague(league);
    }
}
