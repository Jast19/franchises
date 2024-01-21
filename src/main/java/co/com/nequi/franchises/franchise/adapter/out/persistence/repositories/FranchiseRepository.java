package co.com.nequi.franchises.franchise.adapter.out.persistence.repositories;

import co.com.nequi.franchises.franchise.adapter.out.persistence.entitys.FranchiseEntity;
import co.com.nequi.franchises.franchise.adapter.out.persistence.reactive.FranchiseReactiveMysql;
import co.com.nequi.franchises.franchise.application.port.out.FranchisePersistence;
import co.com.nequi.franchises.franchise.domain.dto.ProductSubsidiary;
import co.com.nequi.franchises.franchise.domain.module.Franchise;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class FranchiseRepository implements FranchisePersistence {

    private final FranchiseReactiveMysql franchiseReactiveMysql;

    @Override
    public Mono<Franchise> save(String name) {
        return this.franchiseReactiveMysql.save(FranchiseEntity.entity(name))
            .map(FranchiseEntity::domain);
    }

    @Override
    public Mono<Franchise> update(Long id, String name) {
        return this.franchiseReactiveMysql.save(new FranchiseEntity(id, name))
            .map(FranchiseEntity::domain);
    }

    @Override
    public Flux<ProductSubsidiary> maxStock(Long franchiseId) {
        return this.franchiseReactiveMysql.findAllProductsByFranchiseId(franchiseId);
    }

}
