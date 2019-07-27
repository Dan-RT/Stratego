package ca.daniel.www.controller;


import ca.daniel.www.exception.ActionNotAuthorizedException;
import ca.daniel.www.model.Turn;
import ca.daniel.www.service.GameService;
import ca.daniel.www.service.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "helloWorld";
    }

    @GetMapping("/game")
    public ResponseEntity game() {
        gameService.initGame();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/turn")
    public ResponseEntity turn(@Valid Turn turn) {
        try {
            turnService.movePieceOnBoard(turn);
            turn.getBoard().display();
        } catch (ActionNotAuthorizedException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


}