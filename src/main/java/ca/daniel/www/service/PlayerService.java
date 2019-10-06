package ca.daniel.www.service;

import ca.daniel.www.dao.PlayerDao;
import ca.daniel.www.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private PlayerDao playerDao;

    @Autowired
    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
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
}
