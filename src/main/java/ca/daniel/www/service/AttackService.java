package ca.daniel.www.service;

import ca.daniel.www.model.Attack;
import ca.daniel.www.model.Piece;
import ca.daniel.www.model.customEnum.PieceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttackService {

    public Attack manageAttack(Attack attack) {

        if (!TurnService.isAuthorized(attack.getBoard(), attack.getPieceAttacking(), attack.getPieceAttacked().getCoordinate(), true)) {
            return attack;
        }

        int rankAttacking = attack.getPieceAttacking().getType().getRank();
        int rankAttacked = attack.getPieceAttacked().getType().getRank();

        if (rankAttacking > rankAttacked) {
            attack.getPlayerAttacked().addPiece(attack.getPieceAttacked());
            attack.setBoard(GameService.setPieceOnBoard(
                    attack.getBoard(),
                    attack.getPieceAttacking().getCoordinate(),
                    attack.getPieceAttacked().getCoordinate(),
                    attack.getPieceAttacking()));
            attack.getPlayerAttacked().update();
        } else if (rankAttacking < rankAttacked) {
            attack.getPlayerAttacking().addPiece(attack.getPieceAttacking());
            attack.setBoard(GameService.setPieceOnBoard(
                    attack.getBoard(),
                    attack.getPieceAttacking().getCoordinate(),
                    attack.getPieceAttacked().getCoordinate(),
                    attack.getPieceAttacked()));
            attack.getPlayerAttacking().update();
        } else {
            attack.getPlayerAttacked().addPiece(attack.getPieceAttacked());
            Piece emptyPiece = new Piece();
            emptyPiece.setType(PieceType.NONE);
            emptyPiece.setCoordinate(attack.getPieceAttacking().getCoordinate());
            attack.setBoard(GameService.setPieceOnBoard(attack.getBoard(), attack.getPieceAttacked().getCoordinate(), attack.getPieceAttacking().getCoordinate(), new Piece()));

            attack.getPlayerAttacking().addPiece(attack.getPieceAttacking());
            Piece emptyPiece2 = new Piece();
            emptyPiece2.setType(PieceType.NONE);
            emptyPiece2.setCoordinate(attack.getPieceAttacked().getCoordinate());
            attack.setBoard(GameService.setPieceOnBoard(attack.getBoard(), attack.getPieceAttacking().getCoordinate(), attack.getPieceAttacked().getCoordinate(), emptyPiece2));

            attack.getPlayerAttacking().update();
            attack.getPlayerAttacked().update();
        }



        return attack;
    }

}
