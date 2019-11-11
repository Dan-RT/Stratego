package ca.daniel.www.controller;

import ca.daniel.www.model.*;
import ca.daniel.www.service.AttackService;
import ca.daniel.www.service.GameService;
import ca.daniel.www.service.TurnService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GameController {

    private GameService gameService;
    private TurnService turnService;
    private AttackService attackService;
    private Logger logger = LoggerFactory.getLogger(GameController.class);

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
    @GetMapping("/game/{id}")
    public Game game(@PathVariable("id") String id) {
        logger.info("GET /game/" + id);
        return gameService.getGame(id);
    }

    @CrossOrigin
    @PostMapping("/game/setup")
    public Game setGame(@ApiParam(value = "game valid object", required = true) @Valid @RequestBody Game game) {
        logger.info("POST /setup");
        return gameService.setGame(game);
    }

    @CrossOrigin
    @GetMapping("/game/initialize")
    public Game initializeGame() {
        logger.info("GET /game/initialize");
        //return gameService.initGame();
        return gameService.initGameOld();
    }

    @CrossOrigin
    @PutMapping("/game")
    public Game updateGame(@ApiParam(value = "game valid object", required = true) @Valid @RequestBody Game game) {
        logger.info("PUT /game");
        return gameService.updateGame(game);
    }

    @CrossOrigin
    @PostMapping("/turn")
    public Turn turn(@ApiParam(value = "turn valid object", required = true) @Valid @RequestBody Turn turn) {
        logger.info("POST /turn");
        Turn resp = turnService.movePieceOnBoard(turn);

        //GameService.displayBoard(resp.getBoard());

        return resp;
    }

    @CrossOrigin
    @PostMapping("/attack")
    public Game attack(@ApiParam(value = "attack valid object", required = true) @Valid @RequestBody Attack attack) {
        logger.info("POST /attack");
        attack = attackService.manageAttack(attack);

        if (attack == null) {
            return null;
        }

        return gameService.updateGame(attack);
    }
}