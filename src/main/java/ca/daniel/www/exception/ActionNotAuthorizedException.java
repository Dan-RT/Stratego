package ca.daniel.www.exception;

import org.apache.log4j.Logger;

public class ActionNotAuthorizedException extends Throwable {
    final static Logger logger = Logger.getLogger(ActionNotAuthorizedException.class);
    public ActionNotAuthorizedException(){
        logger.error("The action is not valid.");
    }
}
