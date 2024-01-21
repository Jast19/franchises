package co.com.nequi.franchises.franchise.adapter.out.persistence.repositories;

import co.com.nequi.franchises.franchise.adapter.out.persistence.entitys.SubsidiaryEntity;
import co.com.nequi.franchises.franchise.adapter.out.persistence.reactive.SubsidiaryReactiveMysql;
import co.com.nequi.franchises.franchise.application.port.out.SubsidiaryPersistence;
import co.com.nequi.franchises.franchise.domain.module.Subsidiary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class SubsidiaryRepository implements SubsidiaryPersistence {

    private final SubsidiaryReactiveMysql subsidiaryReactiveMysql;

    @Autowired
    public SubsidiaryRepository(SubsidiaryReactiveMysql subsidiaryReactiveMysql) {
        this.subsidiaryReactiveMysql = subsidiaryReactiveMysql;
    }

    @Override
    public Mono<Subsidiary> save(Subsidiary subsidiary) {
        return this.subsidiaryReactiveMysql.save(SubsidiaryEntity.newEntity(subsidiary))
            .map(SubsidiaryEntity::domain);
    }

    @Override
    public Mono<Boolean> updateName(Long subsidiaryId, String name) {
        return this.subsidiaryReactiveMysql.updateName(name, subsidiaryId)
            .filter(value -> value == 1)
            .map(success -> Boolean.TRUE);
    }
}
