package co.com.nequi.franchises.franchise.domain.exceptions;

import co.com.nequi.franchises.franchise.domain.enums.TypeException;
import lombok.Getter;

@Getter
public class FranchiseException extends RuntimeException {

    private final TypeException type;

    public FranchiseException(TypeException type, String message) {
        super(message);
        this.type = type;
    }

    public FranchiseException(TypeException type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

    public FranchiseException(TypeException type, Throwable cause) {
        super(cause);
        this.type = type;
    }
}
