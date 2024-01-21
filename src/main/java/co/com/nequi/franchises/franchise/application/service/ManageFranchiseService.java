package co.com.nequi.franchises.franchise.application.service;

import co.com.nequi.franchises.franchise.application.port.in.ManageFranchiseUseCase;
import co.com.nequi.franchises.franchise.application.port.out.FranchisePersistence;
import co.com.nequi.franchises.franchise.domain.dto.ProductSubsidiary;
import co.com.nequi.franchises.franchise.domain.enums.TypeException;
import co.com.nequi.franchises.franchise.domain.module.Franchise;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
    public Mono<Franchise> update(Long id, String name) {
        return this.franchisePersistence.update(id, name)
            .onErrorResume(trow ->
                Mono.error(TypeException.FRANCHISE_NO_FOUND.build(id.toString(), trow)));
    }

    @Override
    public Mono<List<ProductSubsidiary>> maxStock(Long franchiseId) {
        return this.franchisePersistence.maxStock(franchiseId)
            .switchIfEmpty(Mono.error(TypeException.PRODUCT_NO_FOUND.build(franchiseId.toString())))
            .groupBy(ProductSubsidiary::getSubsidiaryId)
            .flatMap(Flux::collectList)
            .map(productsList -> productsList.stream()
                .max(Comparator.comparing(ProductSubsidiary::getStock))
                .orElse(null))
            .collectList();
    }
}
