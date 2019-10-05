package ca.daniel.www.service;

import ca.daniel.www.model.Attack;
import ca.daniel.www.model.Piece;
import ca.daniel.www.model.customEnum.PieceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttackService {

    private GameService gameService;

    @Autowired
    public AttackService(GameService gameService) {
        this.gameService = gameService;
    }

    public Attack manageAttack(Attack attack) {

        if (!TurnService.isAuthorized(attack.getBoard(), attack.getPieceAttacking(), attack.getPieceAttacked().getCoordinate(), true)) {
            return attack;
        }

        int rankAttacking = attack.getPieceAttacking().getType().getRank();
        int rankAttacked = attack.getPieceAttacked().getType().getRank();

        if (rankAttacking > rankAttacked) {
            attack.setBoard(gameService.setPieceOnBoard(
                    attack.getBoard(),
                    attack.getPieceAttacking().getCoordinate(),
                    attack.getPieceAttacked().getCoordinate(),
                    attack.getPieceAttacking()));
        } else if (rankAttacking < rankAttacked) {
            attack.setBoard(gameService.setPieceOnBoard(
                    attack.getBoard(),
                    attack.getPieceAttacking().getCoordinate(),
                    attack.getPieceAttacked().getCoordinate(),
                    attack.getPieceAttacked()));
        } else {

            Piece emptyPiece = new Piece();
            emptyPiece.setType(PieceType.NONE);
            emptyPiece.setCoordinate(attack.getPieceAttacking().getCoordinate());
            attack.setBoard(gameService.setPieceOnBoard(attack.getBoard(), attack.getPieceAttacked().getCoordinate(), attack.getPieceAttacking().getCoordinate(), new Piece()));


            Piece emptyPiece2 = new Piece();
            emptyPiece2.setType(PieceType.NONE);
            emptyPiece2.setCoordinate(attack.getPieceAttacked().getCoordinate());
            attack.setBoard(gameService.setPieceOnBoard(attack.getBoard(), attack.getPieceAttacking().getCoordinate(), attack.getPieceAttacked().getCoordinate(), emptyPiece2));
        }

        return attack;
    }

}
