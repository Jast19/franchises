package co.com.nequi.franchises.franchise.adapter.out.persistence.reactive;

import co.com.nequi.franchises.franchise.adapter.out.persistence.entitys.FranchiseEntity;
import co.com.nequi.franchises.franchise.domain.dto.ProductSubsidiary;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface FranchiseReactiveMysql extends ReactiveCrudRepository<FranchiseEntity, Long> {

    @Query("SELECT p.product_id, p.subsidiary_id, p.name as product, p.stock, s.name as subsidiary " + "FROM Product p "
        + "JOIN Subsidiary s ON p.subsidiary_id = s.subsidiary_id " + "WHERE s.franchise_id = :franchiseId")
    Flux<ProductSubsidiary> findAllProductsByFranchiseId(@Param("franchiseId") Long franchiseId);

}
