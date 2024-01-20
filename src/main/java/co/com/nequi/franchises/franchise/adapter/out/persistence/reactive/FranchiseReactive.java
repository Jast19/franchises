package co.com.nequi.franchises.franchise.adapter.out.persistence.reactive;

import co.com.nequi.franchises.franchise.adapter.out.persistence.entitys.FranchiseEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface FranchiseReactive extends ReactiveCrudRepository<FranchiseEntity, Long> {

    @Query("INSERT INTO Franchise (name) VALUES (:name)")
    Mono<FranchiseEntity> saveFranchise(@Param("name") String name);
}
