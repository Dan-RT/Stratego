package ca.daniel.www.controller;

import ca.daniel.www.exception.ActionNotAuthorizedException;
import ca.daniel.www.model.*;
import ca.daniel.www.service.GameService;
import ca.daniel.www.service.TurnService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GameController {

    @Autowired
    GameService gameService;
    @Autowired
    TurnService turnService;

    public GameController (GameService gameService, TurnService turnService) {
        this.gameService = gameService;
        this.turnService = turnService;
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
}