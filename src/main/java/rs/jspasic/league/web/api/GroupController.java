package rs.jspasic.league.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rs.jspasic.league.model.Group;
import rs.jspasic.league.model.League;
import rs.jspasic.league.service.GroupService;
import rs.jspasic.league.service.LeagueService;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private LeagueService leagueService;

    @GetMapping("/api/{leagueId}/groups")
    public List<Group> getGroups(@PathVariable(name = "leagueId") Long leagueId) {
        return groupService.findByLeagueId(leagueId);
    }

    @PostMapping("/api/{leagueId}/groups")
    public Group addGroup(@PathVariable(name = "leagueId") Long leagueId, @RequestBody Group group) {
        League league = leagueService.findLeagueById(leagueId);
        group.setLeague(league);
        Group g = groupService.addGroup(group);
        return g;
    }

    @PostMapping("/api/{leagueId}/groups/multiple")
    public List<Group> addMultipleGroups(@PathVariable(name = "leagueId") Long leagueId, @RequestBody List<Group> groups) {
        League league = leagueService.findLeagueById(leagueId);
        groups.forEach(g -> g.setLeague(league));
        return groupService.addGroups(groups);
    }
}
