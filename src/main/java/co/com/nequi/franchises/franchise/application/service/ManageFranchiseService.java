package co.com.nequi.franchises.franchise.application.service;

import co.com.nequi.franchises.franchise.application.port.in.ManageFranchiseUseCase;
import co.com.nequi.franchises.franchise.application.port.out.FranchisePersistence;
import co.com.nequi.franchises.franchise.domain.module.Franchise;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ManageFranchiseService implements ManageFranchiseUseCase {

    private final FranchisePersistence franchisePersistence;

    @Override
    public Mono<Franchise> create(String name) {
        return this.franchisePersistence.save(name);
    }

    @Override
    public Mono<Franchise> update(Franchise franchise) {
        return this.franchisePersistence.update(franchise);
    }
}
