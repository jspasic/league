package rs.jspasic.league.model;

import java.util.List;

public class GroupStandings {

    private String leagueTitle;
    private String groupName;
    private Integer matchday;
    private List<StandingsEntry> standings;

    public String getLeagueTitle() {
        return leagueTitle;
    }

    public void setLeagueTitle(String leagueTitle) {
        this.leagueTitle = leagueTitle;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    public List<StandingsEntry> getStandings() {
        return standings;
    }

    public void setStandings(List<StandingsEntry> standings) {
        this.standings = standings;
    }
}
