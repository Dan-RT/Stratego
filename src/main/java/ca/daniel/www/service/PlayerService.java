package ca.daniel.www.service;

import ca.daniel.www.dao.GameDao;
import ca.daniel.www.dao.PlayerDao;
import ca.daniel.www.model.Game;
import ca.daniel.www.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private PlayerDao playerDao;
    private GameDao gameDao;
    private GameService gameService;

    @Autowired
    public PlayerService(PlayerDao playerDao, GameDao gameDao, GameService gameService) {
        this.playerDao = playerDao;
        this.gameDao = gameDao;
        this.gameService = gameService;
    }

    public Player getPlayer(String id) {
        return this.playerDao.getPlayerById(id);
    }

    public List<Player> getAllPlayers() {
        return this.playerDao.getAllPlayers();
    }

    public Player savePlayer(Player player) {
        return this.playerDao.savePlayer(player);
    }

    public Game getOpponent(String playerId) {
        List<Player> players = playerDao.getAllPlayers();
        Player current = playerDao.getPlayerById(playerId);
        Player futureOpponent = null;

        if (current == null) {
            return null;
        }

        Game gameAssigned = hadAssignedGame(current);
        if(gameAssigned != null) {
            return gameAssigned;
        }

        for (Player opponent:players) {
            if (opponent.getReady() && !opponent.get_id().equals(current.get_id())) {
                futureOpponent = opponent;
                break;
            }
        }

        current.setReady(true);
        playerDao.updatePlayer(current);

        if (futureOpponent == null) {
            return null;
        }

        return gameService.initGame(current, futureOpponent);
    }

    private Game hadAssignedGame(Player currentPlayer) {

        List<Game> games = gameDao.getAllGames();

        for (Game gameTmp:games) {
            for (Player playerTmp:gameTmp.getPlayers()) {
                if (playerTmp.get_id().equals(currentPlayer.get_id())) {
                    return gameTmp;
                }
            }
        }

        return null;
    }
}
