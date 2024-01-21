package co.com.nequi.franchises.franchise.adapter.in.api.request;

import co.com.nequi.franchises.franchise.domain.module.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {

    @NotNull
    private Long subsidiaryId;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private Integer stock;

    public static Product domain(ProductCreateRequest request) {
        return new Product(null, request.getSubsidiaryId(), request.getName(), request.getStock());
    }

}
