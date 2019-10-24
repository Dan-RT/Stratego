package ca.daniel.www.controller;

import ca.daniel.www.model.*;
import ca.daniel.www.service.AttackService;
import ca.daniel.www.service.GameService;
import ca.daniel.www.service.TurnService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GameController {

    private GameService gameService;
    private TurnService turnService;
    private AttackService attackService;

    @Autowired
    public GameController(GameService gameService, TurnService turnService, AttackService attackService) {
        this.gameService = gameService;
        this.turnService = turnService;
        this.attackService = attackService;
    }

    @CrossOrigin
    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello World!";
    }

    @CrossOrigin
    @GetMapping("/game")
    public Game game() {
        return null;
        //return gameService.setGame(game);
    }

    @CrossOrigin
    @PostMapping("/game/setup")
    public Game setGame(@ApiParam(value = "game valid object", required = true) @Valid @RequestBody Game game) {

        gameService.setGame(game);

        return gameService.setGame(game);
    }

    @CrossOrigin
    @GetMapping("/game/initialize")
    public Game initializeGame() {
        //return gameService.initGame();
        return gameService.initGameOld();
    }

    @CrossOrigin
    @PutMapping("/game")
    public Game updateGame(@ApiParam(value = "game valid object", required = true) @Valid @RequestBody Game game) {
        return gameService.updateGame(game);
    }

    @CrossOrigin
    @PostMapping("/turn")
    public Turn turn(@ApiParam(value = "turn valid object", required = true) @Valid @RequestBody Turn turn) {
        Turn resp = turnService.movePieceOnBoard(turn);

        //GameService.displayBoard(resp.getBoard());

        return resp;
    }

    @CrossOrigin
    @PostMapping("/attack")
    public Game attack(@ApiParam(value = "attack valid object", required = true) @Valid @RequestBody Attack attack) {

        attack = attackService.manageAttack(attack);

        if (attack == null) {
            return null;
        }

        return gameService.updateGame(attack);
    }
}