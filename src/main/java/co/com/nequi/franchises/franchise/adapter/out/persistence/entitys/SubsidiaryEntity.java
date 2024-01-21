package co.com.nequi.franchises.franchise.adapter.out.persistence.entitys;

import co.com.nequi.franchises.franchise.domain.module.Subsidiary;
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

    public static SubsidiaryEntity newEntity(Subsidiary subsidiary) {
        return new SubsidiaryEntity(null, subsidiary.getFranchiseId(), subsidiary.getName());
    }

    public static SubsidiaryEntity entity(Subsidiary subsidiary) {
        return new SubsidiaryEntity(subsidiary.getId(), subsidiary.getFranchiseId(), subsidiary.getName());
    }

    public static Subsidiary domain(SubsidiaryEntity entity) {
        return new Subsidiary(entity.getId(), entity.getFranchiseId(), entity.getName());
    }
}
