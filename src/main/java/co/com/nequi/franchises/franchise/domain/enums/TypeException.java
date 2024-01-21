package co.com.nequi.franchises.franchise.domain.enums;

import co.com.nequi.franchises.franchise.domain.exceptions.FranchiseException;
import lombok.Getter;

@Getter
public enum TypeException {

    BAD_REQUEST(400, "Bad request : "),
    FRANCHISE_NO_FOUND(404, "Franchise no found : "),
    SUBSIDIARY_NO_FOUND(404, "Subsidiary no found : "),
    PRODUCT_NO_FOUND(404, "Product no found : ");

    private final int code;
    private final String message;

    TypeException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public FranchiseException build() {
        return new FranchiseException(this, this.getMessage());
    }

    public FranchiseException build(String value) {
        return new FranchiseException(this, this.getMessage() + value);
    }

    public FranchiseException build(String value, Throwable e) {
        return new FranchiseException(this, this.getMessage() + value, e);
    }
}
