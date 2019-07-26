package ca.daniel.www.service;

import ca.daniel.www.model.Game;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public void initGame() {
        Game game = new Game();
        game.init();
        game.randomSet();
        game.getBoard().display();
    }
}
