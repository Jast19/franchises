package co.com.nequi.franchises.franchise.adapter.out.persistence.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Subsidiary")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubsidiaryEntity {

    @Id
    @Column("subsidiary_id")
    private Long id;
    @Column("franchise_id")
    private Long franchiseId;
    private String name;
}
