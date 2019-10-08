package ca.daniel.www.dao;

import ca.daniel.www.model.Game;
import ca.daniel.www.repository.GameRepository;
import org.springframework.stereotype.Component;

@Component
public class GameDao {
    private GameRepository gameRepository;

    public GameDao(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }
    
    public Game updateGame(Game game) {
        Game updatedGame = gameRepository.findBy_id(game.get_id());
        
        updatedGame.set_id(game.get_id());
        updatedGame.setBoard(game.getBoard());
        updatedGame.setPlayers(game.getPlayers());
        
        return gameRepository.save(updatedGame);
    }

    public Game getGameById(String id) {
        return gameRepository.findBy_id(id);
    }
}