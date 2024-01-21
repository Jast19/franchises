package co.com.nequi.franchises.franchise.adapter.in.api.handler;

import co.com.nequi.franchises.franchise.adapter.in.api.response.Response;
import co.com.nequi.franchises.franchise.domain.exceptions.FranchiseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(FranchiseException.class)
    public Mono<ResponseEntity<Response<String>>> franchiseException(FranchiseException ex) {
        log.error(":: FranchiseException : {0} ", ex);
        HttpStatus httpStatus = HttpStatus.valueOf(ex.getType().getCode());
        return Mono.just(ResponseEntity.status(httpStatus)
            .body(Response.<String>builder()
                .code(httpStatus.value())
                .message(httpStatus.name())
                .error(ex.getMessage())
                .build()));
    }

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<Response<String>>> handleException(RuntimeException exception) {
        log.error(":: Error : {0} ", exception);
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.<String>builder()
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
            .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
            .build()));
    }

}
