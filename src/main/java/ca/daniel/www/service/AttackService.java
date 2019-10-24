package ca.daniel.www.service;

import ca.daniel.www.dao.PlayerDao;
import ca.daniel.www.model.Attack;
import ca.daniel.www.model.Piece;
import ca.daniel.www.model.customEnum.PieceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttackService {

    private PlayerDao playerDao;

    @Autowired
    public AttackService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public Attack manageAttack(Attack attack) {

        if (!TurnService.isAuthorized(attack.getBoard(), attack.getPieceAttacking(), attack.getPieceAttacked().getCoordinate(), true)) {
            return null;
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
            playerDao.updatePlayer(attack.getPlayerAttacked());
        } else if (rankAttacking < rankAttacked) {
            attack.getPlayerAttacking().addPiece(attack.getPieceAttacking());
            attack.setBoard(GameService.setPieceOnBoard(
                    attack.getBoard(),
                    attack.getPieceAttacking().getCoordinate(),
                    attack.getPieceAttacked().getCoordinate(),
                    attack.getPieceAttacked()));
            playerDao.updatePlayer(attack.getPlayerAttacking());
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

            playerDao.updatePlayer(attack.getPlayerAttacked());
            playerDao.updatePlayer(attack.getPlayerAttacking());
        }

        return attack;
    }

}
