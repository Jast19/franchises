package co.com.nequi.franchises.franchise.adapter.out.persistence.entitys;

import co.com.nequi.franchises.franchise.domain.module.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @Column("product_id")
    private Long id;
    @Column("subsidiary_id")
    private Long subsidiaryId;
    private String name;
    private Integer stock;

    public static ProductEntity entity(Product product){
        return new ProductEntity(null, product.getSubsidiaryId(), product.getName(), product.getStock());
    }

    public static Product domain(ProductEntity entity){
        return new Product(entity.getId(), entity.getSubsidiaryId(), entity.getName(), entity.getStock());
    }
}
