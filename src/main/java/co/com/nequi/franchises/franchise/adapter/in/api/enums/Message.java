package co.com.nequi.franchises.franchise.adapter.in.api.enums;

import lombok.Getter;

@Getter
public enum Message {

    FRANCHISE_CREATED( "Franchise successfully created"),
    FRANCHISE_UPDATE( "Franchise successfully updated"),
    SUBSIDIARY_CREATED( "Subsidiary successfully created"),
    SUBSIDIARY_UPDATE( "Subsidiary successfully updated"),
    PRODUCT_CREATED( "Product successfully created"),
    PRODUCT_UPDATE( "Product successfully updated");

    private final String value;

    Message(String value) {
        this.value = value;
    }
}
