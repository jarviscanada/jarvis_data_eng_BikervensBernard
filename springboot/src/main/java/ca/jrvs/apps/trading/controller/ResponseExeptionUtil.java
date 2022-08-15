package ca.jrvs.apps.trading.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResponseExeptionUtil {
    private static final Logger logger = LoggerFactory.getLogger((ResponseExeptionUtil.class));

    public static ResponseStatusException getResponseStatusExecption(Exception ex) {
        if( ex instanceof IllegalArgumentException) {
            logger.debug("Invalid input", ex);
            return new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }else {
            logger.error("Internal Error", ex);
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal Server Error: please contact admin");
        }
    }
}
