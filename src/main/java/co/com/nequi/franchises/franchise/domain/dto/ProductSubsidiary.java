package co.com.nequi.franchises.franchise.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSubsidiary {

    @Column("product_id")
    private Long productId;
    @Column("subsidiary_id")
    private Long subsidiaryId;
    private String product;
    private Integer stock;
    private String subsidiary;
}
