package vip.xelapedia.discgolf.catalog.internal.error;

import org.springframework.http.HttpStatus;
import vip.xelapedia.discgolf.common.error.CommonRestException;

import java.text.MessageFormat;
import java.util.UUID;

public class MoldNotFoundException extends CommonRestException {
    public MoldNotFoundException(final UUID id) {
        super(HttpStatus.NOT_FOUND, MessageFormat.format("Mold not found id {0}", id));
    }
}
