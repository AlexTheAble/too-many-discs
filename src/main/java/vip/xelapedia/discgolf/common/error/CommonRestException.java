package vip.xelapedia.discgolf.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class CommonRestException extends RuntimeException {
    final HttpStatus status;

    protected CommonRestException(final HttpStatus status, final String message) {
        super(message);
        this.status = status;
    }
}
