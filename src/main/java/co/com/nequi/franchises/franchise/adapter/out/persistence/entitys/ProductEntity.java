package co.com.nequi.franchises.franchise.adapter.out.persistence.entitys;

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

}
