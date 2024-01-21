package co.com.nequi.franchises.franchise.adapter.in.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateStockRequest {

    @NotNull
    private Long productId;

    @NotNull
    private Integer stock;
}
