package rs.jspasic.league.model;

public class StandingsEntry {

    private int rank;
    private String team;
    private int playedGames;
    private int points;
    private int goals;
    private int goalsAgainst;
    private int goalDifference;
    private int win;
    private int lose;
    private int draw;

    public StandingsEntry(String team) {
        this.team = team;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void incrementPlayedGames() {
        this.playedGames++;
    }

    public void addGoals(int goals) {
        this.goals += goals;
    }

    public void addWinAndUpdatePoints() {
        this.win++;
        this.points += 3;
    }

    public void addLoss() {
        this.lose++;
    }

    public void addDrawAndUpdatePoints() {
        this.draw++;
        this.points++;
    }

    public void incrementGoalsForAndUpdateDifference(int goalsFor) {
        this.goals += goalsFor;
        this.goalDifference = this.goals - this.goalsAgainst;
    }

    public void incrementGoalsAgainstAndUpdateDifference(int goalsAgains) {
        this.goalsAgainst += goalsAgains;
        this.goalDifference = this.goals - this.goalsAgainst;
    }
}
