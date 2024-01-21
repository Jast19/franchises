package co.com.nequi.franchises.franchise.application.port.out;

import co.com.nequi.franchises.franchise.domain.module.Product;
import reactor.core.publisher.Mono;

public interface ProductPersistence {

    Mono<Product> save(Product product);

    Mono<Boolean> updateName(Long productId, String name);

    Mono<Boolean> updateStock(Long productId, Integer stock);
}
