package ca.daniel.www.controller;


import ca.daniel.www.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    GameService gameService;

    public GameController (GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/helloWorld")
    public String helloWorld() {
        return "helloWorld";
    }

    @RequestMapping("/game")
    public ResponseEntity game() {
        gameService.initGame();
        return new ResponseEntity(HttpStatus.OK);
    }

}