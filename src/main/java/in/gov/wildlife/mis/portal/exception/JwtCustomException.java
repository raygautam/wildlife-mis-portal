package in.gov.wildlife.mis.portal.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class JwtCustomException extends RuntimeException{
    private final Error error;

    public JwtCustomException(String msg, Error error) {
        super(msg);
        this.error = error;
    }

}
