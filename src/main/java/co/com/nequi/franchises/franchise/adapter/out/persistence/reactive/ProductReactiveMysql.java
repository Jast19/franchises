package co.com.nequi.franchises.franchise.adapter.out.persistence.reactive;

import co.com.nequi.franchises.franchise.adapter.out.persistence.entitys.ProductEntity;
import co.com.nequi.franchises.franchise.domain.dto.ProductSubsidiary;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductReactiveMysql extends ReactiveCrudRepository<ProductEntity, Long> {

    @Modifying
    @Query("UPDATE Product SET name = :name WHERE product_id = :productId")
    Mono<Long> updateName(@Param("name") String name, @Param("productId") Long productId);

    @Modifying
    @Query("UPDATE Product SET stock = :stock WHERE product_id = :productId")
    Mono<Long> updateStock(@Param("stock") Integer stock, @Param("productId") Long productId);

    @Query("SELECT p.product_id, p.subsidiary_id, p.name as product, p.stock, s.name as subsidiary " +
        "FROM Product p " +
        "JOIN Subsidiary s ON p.subsidiary_id = s.subsidiary_id " +
        "WHERE s.franchise_id = :franchiseId")
    Flux<ProductSubsidiary> findAllProductsByFranchiseId(@Param("franchiseId") Long franchiseId);

}
