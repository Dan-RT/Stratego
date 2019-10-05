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
        return "helloWorld";
    }

    @CrossOrigin
    @GetMapping("/game")
    public Game game() {
        return gameService.setGame();
    }

    @CrossOrigin
    @GetMapping("/game/initialize")
    public Game initializeGame() {

        Game game = gameService.initGame();

        return game;
    }

    @CrossOrigin
    @PostMapping("/turn")
    public Turn turn(@ApiParam(value = "turn valid object", required = true) @Valid @RequestBody Turn turn) {
        Turn resp = turnService.movePieceOnBoard(turn);

        GameService.displayBoard(resp.getBoard());

        return resp;
    }

    @CrossOrigin
    @PostMapping("/attack")
    public Attack attack(@ApiParam(value = "attack valid object", required = true) @Valid @RequestBody Attack attack) {

        attack = attackService.manageAttack(attack);

        GameService.displayBoard(attack.getBoard());

        return attack;
    }
}