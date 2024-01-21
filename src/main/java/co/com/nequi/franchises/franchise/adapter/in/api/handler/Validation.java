package co.com.nequi.franchises.franchise.adapter.in.api.handler;

import co.com.nequi.franchises.franchise.domain.enums.TypeException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Validation {

    private final Validator validator;

    public Validation(Validator validator) {
        this.validator = validator;
    }

    public <T> Mono<T> validate(T t) {
        var error = this.validator.validate(t).stream()
            .map(violation -> violation.getPropertyPath() + ", " + violation.getMessage()).toList();
        if (Boolean.FALSE.equals(error.isEmpty())) {
            throw TypeException.BAD_REQUEST.build(error.toString());
        }
        return Mono.just(t);
    }

    public Mono<String> requireNonNull(String value) {
        if (Boolean.TRUE.equals(valueIsNull(value))) {
            throw TypeException.BAD_REQUEST.build("name");
        }
        return Mono.just(value);
    }

    private Boolean valueIsNull(String value) {
        if (value == null || value.isBlank()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
