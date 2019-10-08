package ca.daniel.www.service;

import ca.daniel.www.dao.GameDao;
import ca.daniel.www.dao.PlayerDao;
import ca.daniel.www.model.Coordinate;
import ca.daniel.www.model.Game;
import ca.daniel.www.model.Piece;
import ca.daniel.www.model.Player;
import ca.daniel.www.model.customEnum.PieceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private GameDao gameDao;
    private PlayerDao playerDao;

    @Autowired
    public GameService(GameDao gameDao, PlayerDao playerDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    public Game initGame(Player player1, Player player2) {
        Game game = new Game();
        game.init();
        player1.setTeam(1);
        player2.setTeam(2);
        playerDao.updatePlayer(player1);
        playerDao.updatePlayer(player2);
        game.addPlayer(player1);
        game.addPlayer(player2);
        return gameDao.saveGame(game);
    }

    public Game initGameOld() {
        Game game = new Game();
        game.init();
        return game;
    }

    public Game updateGame(Game game) {
        return gameDao.updateGame(game);
    }

    public Game setGame() {
        Game game = new Game();
        game.init();
        //game.randomSet();
        return game;
    }

    public static Piece[][] initBoard() {
        Piece[][] board = new Piece[10][10];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if ((i == 2 || i == 3 || i == 6 || i == 7) && (j == 4 || j == 5)) {
                    Piece piece = new Piece();
                    piece.setType(PieceType.LAKE);
                    piece.setCoordinate(new Coordinate(i, j));
                    board[i][j] = piece;
                } else {
                    Piece piece = new Piece();
                    piece.setType(PieceType.NONE);
                    piece.setCoordinate(new Coordinate(i, j));
                    board[i][j] = piece;
                }
            }
        }

        return board;
    }

    public static void displayBoard(Piece[][] board) {
        System.out.print("\n");
        for(int i = 0; i < board.length; i++) {
            System.out.print("\n");
            for(int j = 0; j < board[i].length; j++) {
                int rank = board[j][i].getType().getRank();
                String output = "|";
                if (rank < 10 && rank >= 0) {
                    output = output + " ";
                }
                output = output + board[j][i].getType().getRank();
                if (j == 9) {
                    output = output + "|";
                }
                System.out.print(output);
            }
        }
        System.out.print("\n");
    }

    public static Piece[][] setPieceOnBoard(Piece[][] board, Coordinate prevCoordinate, Coordinate coordinate, Piece piece) {

        if (prevCoordinate != null) {

            Piece prev_ = board[prevCoordinate.getX()][prevCoordinate.getY()];
            Piece new_ = board[coordinate.getX()][coordinate.getY()];

            //System.out.println(prev_.toString());
            //System.out.println(new_.toString());

            Piece pieceTmp = new Piece();
            pieceTmp.setType(PieceType.NONE);
            pieceTmp.setCoordinate(new Coordinate(prevCoordinate.getX(), prevCoordinate.getY()));
            board[prevCoordinate.getX()][prevCoordinate.getY()] = pieceTmp;

            //this.board[prevCoordinate.getY()][prevCoordinate.getX()] = new Piece(new ObjectId(), PieceType.NONE, new Coordinate(prevCoordinate.getY(), prevCoordinate.getX()));
        }
        piece.setCoordinate(coordinate);
        board[coordinate.getX()][coordinate.getY()] = piece;

        return board;
    }

    public static void setPieces(Piece[][] board, List<Piece> pieces) {
        for (Piece piece : pieces) {
            if (TurnService.squareIsEmpty(board, piece.getCoordinate())) {
                setPieceOnBoard(board,null, piece.getCoordinate(), piece);
            }
        }
    }

}
