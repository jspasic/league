package rs.jspasic.league.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;
    @Column(name = "matchday")
    private Integer matchday;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;
    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;
    @Column(name = "kickoff_at")
    private Date kickoffAt;
    @Column(name = "home_team_goals")
    private Integer homeTeamGoals;
    @Column(name = "away_team_goals")
    private Integer awayTeamGoals;

    public Game() {

    }

    public Game(Long id, League league, Integer matchday, Group group, Team homeTeam, Team awayTeam, Date kickoffAt, Integer homeTeamGoals, Integer awayTeamGoals) {
        this.id = id;
        this.league = league;
        this.matchday = matchday;
        this.group = group;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.kickoffAt = kickoffAt;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Date getKickoffAt() {
        return kickoffAt;
    }

    public void setKickoffAt(Date kickOffAt) {
        this.kickoffAt = kickOffAt;
    }

    public Integer getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(Integer homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public boolean isDraw() {
        return homeTeamGoals.intValue() == awayTeamGoals.intValue();
    }

    public boolean isHomeTeamWin() {
        return homeTeamGoals.intValue() > awayTeamGoals.intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
                league.equals(game.league) &&
                Objects.equals(matchday, game.matchday) &&
                group.equals(game.group) &&
                homeTeam.equals(game.homeTeam) &&
                awayTeam.equals(game.awayTeam) &&
                Objects.equals(kickoffAt, game.kickoffAt) &&
                homeTeamGoals.equals(game.homeTeamGoals) &&
                awayTeamGoals.equals(game.awayTeamGoals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, league, matchday, group, homeTeam, awayTeam, kickoffAt, homeTeamGoals, awayTeamGoals);
    }
}
