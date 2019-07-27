package ca.daniel.www.exception;

import org.apache.log4j.Logger;

public class SquareNotEmptyException extends Throwable {
    final static Logger logger = Logger.getLogger(SquareNotEmptyException.class);
    public SquareNotEmptyException(){
        logger.error("The square you want to put your piece on is not available or valid.");
    }
}
