package co.com.nequi.franchises.franchise.adapter.out.persistence.repositories;

import co.com.nequi.franchises.franchise.adapter.out.persistence.entitys.FranchiseEntity;
import co.com.nequi.franchises.franchise.adapter.out.persistence.reactive.FranchiseReactive;
import co.com.nequi.franchises.franchise.application.port.out.FranchisePersistence;
import co.com.nequi.franchises.franchise.domain.module.Franchise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class FranchiseRepository implements FranchisePersistence {

    private FranchiseReactive franchiseReactive;

    @Autowired
    public FranchiseRepository(FranchiseReactive franchiseReactive) {
        this.franchiseReactive = franchiseReactive;
    }

    @Override
    public Mono<Franchise> save(String name) {
        return this.franchiseReactive.save(FranchiseEntity.entity(name))
            .map(FranchiseEntity::domain);
    }

    @Override
    public Mono<Franchise> update(Franchise franchise) {
        return this.franchiseReactive.save(FranchiseEntity.entity(franchise))
            .map(FranchiseEntity::domain);
    }

}
