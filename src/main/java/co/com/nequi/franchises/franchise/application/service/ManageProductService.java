package co.com.nequi.franchises.franchise.application.service;

import co.com.nequi.franchises.franchise.application.port.in.ManageProductUseCase;
import co.com.nequi.franchises.franchise.application.port.out.ProductPersistence;
import co.com.nequi.franchises.franchise.domain.enums.TypeException;
import co.com.nequi.franchises.franchise.domain.module.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ManageProductService implements ManageProductUseCase {

    private final ProductPersistence productPersistence;

    @Override
    public Mono<Product> create(Product product) {
        return this.productPersistence.save(product);
    }

    @Override
    public Mono<Boolean> updateName(Long productId, String name) {
        return this.productPersistence.updateName(productId, name)
            .switchIfEmpty(Mono.error(TypeException.PRODUCT_NO_FOUND.build(productId.toString())));
    }

    @Override
    public Mono<Boolean> updateStock(Long productId, Integer stock) {
        return this.productPersistence.updateStock(productId, stock)
            .switchIfEmpty(Mono.error(TypeException.PRODUCT_NO_FOUND.build(productId.toString())));
    }
}
