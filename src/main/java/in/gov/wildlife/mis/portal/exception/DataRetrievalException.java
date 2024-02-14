package in.gov.wildlife.mis.portal.exception;

import lombok.Getter;

@Getter
public class DataRetrievalException extends RuntimeException {
    private final Error error;

    public DataRetrievalException(String msg, Error error) {
        super(msg);
        this.error = error;
    }
}
