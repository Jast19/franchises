package co.com.nequi.franchises.franchise.application.port.out;

import co.com.nequi.franchises.franchise.domain.module.Franchise;
import reactor.core.publisher.Mono;

public interface FranchisePersistence {

    Mono<Franchise> save(String name);
    Mono<Franchise> update(Franchise franchise);
}
