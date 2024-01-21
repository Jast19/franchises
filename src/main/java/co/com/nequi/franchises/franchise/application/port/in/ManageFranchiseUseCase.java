package co.com.nequi.franchises.franchise.application.port.in;

import co.com.nequi.franchises.franchise.domain.dto.ProductSubsidiary;
import co.com.nequi.franchises.franchise.domain.module.Franchise;
import java.util.List;
import reactor.core.publisher.Mono;

public interface ManageFranchiseUseCase {

    Mono<Franchise> create(String name);

    Mono<Franchise> update(Long id, String name);

    Mono<List<ProductSubsidiary>> maxStock(Long franchiseId);

}
