package co.com.nequi.franchises.franchise.adapter.out.persistence.entitys;

import co.com.nequi.franchises.franchise.domain.module.Franchise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Franchise")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseEntity {

    @Id()
    @Column("franchise_id")

    private Long id;
    @Column("name")
    private String name;

    public static FranchiseEntity entity(String name){
        return new FranchiseEntity(null, name);
    }

    public static FranchiseEntity entity(Franchise franchise) {
        return new FranchiseEntity(franchise.getId(), franchise.getName());
    }

    public static Franchise domain(FranchiseEntity entity) {
        return new Franchise(entity.getId(), entity.getName());
    }
}
