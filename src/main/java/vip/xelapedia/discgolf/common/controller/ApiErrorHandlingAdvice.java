package vip.xelapedia.discgolf.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;
import vip.xelapedia.discgolf.common.error.CommonRestException;
import vip.xelapedia.discgolf.common.error.dto.ApiErrorResponse;

@RestControllerAdvice
@Slf4j
public class ApiErrorHandlingAdvice {
    @ExceptionHandler(CommonRestException.class)
    public final ResponseEntity<ApiErrorResponse> handleErrors(final Exception ex, final WebRequest webRequest) {
        final HttpHeaders headers = new HttpHeaders();

        if (ex instanceof CommonRestException restException) {

            return handleExceptionInternal(restException, new ApiErrorResponse(restException.getMessage()), headers, restException.getStatus(), webRequest);
        } else {
            return handleExceptionInternal(ex, null, headers, HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
        }
    }

    private ResponseEntity<ApiErrorResponse> handleExceptionInternal(final Exception ex,
                                                                     final ApiErrorResponse body,
                                                                     final HttpHeaders headers,
                                                                     final HttpStatus status,
                                                                     final WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
