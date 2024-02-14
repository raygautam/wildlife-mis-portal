package in.gov.wildlife.mis.portal.exception;

import lombok.Getter;

@Getter
public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message) {
        super(message);
    }
}
