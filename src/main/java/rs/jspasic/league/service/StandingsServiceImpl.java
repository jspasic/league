package rs.jspasic.league.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StandingsServiceImpl implements StandingsService {

    @Autowired
    private GameService gameService;

}
