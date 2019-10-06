package ca.daniel.www.dao;

import ca.daniel.www.model.Player;
import ca.daniel.www.repository.PlayerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerDao {
    private PlayerRepository playerRepository;

    public PlayerDao(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player getPlayerById(String id) {
        return playerRepository.findBy_id(id);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}