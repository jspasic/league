package rs.jspasic.league.exception;

public class TeamNotFoundException extends BaseLeagueException {
    public TeamNotFoundException() {

    }

    public TeamNotFoundException(String message) {
        super(message);
    }
}
