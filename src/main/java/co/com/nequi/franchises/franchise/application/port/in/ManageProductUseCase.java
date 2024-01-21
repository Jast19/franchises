package co.com.nequi.franchises.franchise.application.port.in;

import co.com.nequi.franchises.franchise.domain.dto.ProductSubsidiary;
import co.com.nequi.franchises.franchise.domain.module.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageProductUseCase {

    Mono<Product> create(Product product);

    Mono<Boolean> updateName(Long productId, String name);

    Mono<Boolean> updateStock(Long productId, Integer stock);

}
