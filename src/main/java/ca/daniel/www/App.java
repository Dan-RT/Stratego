package ca.daniel.www;

import ca.daniel.www.model.Game;


public class App 
{
    public static void main( String[] args ) {
        Game game = new Game();
        game.init();
        game.randomSet();
        game.getBoard().display();
    }
}
