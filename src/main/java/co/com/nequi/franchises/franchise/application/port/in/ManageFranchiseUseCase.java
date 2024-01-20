package co.com.nequi.franchises.franchise.application.port.in;

import co.com.nequi.franchises.franchise.domain.module.Franchise;
import reactor.core.publisher.Mono;

public interface ManageFranchiseUseCase {

    Mono<Franchise> create(String name);
    Mono<Franchise> update(Franchise franchise);

}
