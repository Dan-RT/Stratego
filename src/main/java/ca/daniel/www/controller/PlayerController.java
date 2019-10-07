package ca.daniel.www.controller;

import ca.daniel.www.exception.OpponentNotReady;
import ca.daniel.www.model.Game;
import ca.daniel.www.model.Player;
import ca.daniel.www.service.PlayerService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @CrossOrigin
    @GetMapping("/player/{id}")
    public Player getPlayer(@PathVariable("id") String id) {
        return this.playerService.getPlayer(id);
    }

    @CrossOrigin
    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return this.playerService.getAllPlayers();
    }

    @CrossOrigin
    @PostMapping("/player")
    public Player addPlayer(@ApiParam(value = "player valid object", required = true) @Valid @RequestBody Player player) {
        return this.playerService.savePlayer(player);
    }

    @CrossOrigin
    @GetMapping("/player/opponent/{id}")
    public Game getOpponent(@PathVariable("id") String id) throws OpponentNotReady {
        Game game = this.playerService.getOpponent(id);
        if (game == null) {
            throw new OpponentNotReady("No ready opponent was found.");
        } else {
            return game;
        }
    }

}
