package ca.daniel.www.dao;

import ca.daniel.www.model.Player;
import ca.daniel.www.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerDao {
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerDao(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Player player) {
        Player updatedPlayer = playerRepository.findBy_id(player.get_id());

        updatedPlayer.set_id(player.get_id());
        updatedPlayer.setName(player.getName());
        updatedPlayer.setTeam(player.getTeam());
        updatedPlayer.setReady(player.getReady());
        updatedPlayer.setPlaying(player.getPlaying());
        updatedPlayer.setPieces(player.getPieces());

        return playerRepository.save(updatedPlayer);
    }

    public Player getPlayerById(String id) {
        return playerRepository.findBy_id(id);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}