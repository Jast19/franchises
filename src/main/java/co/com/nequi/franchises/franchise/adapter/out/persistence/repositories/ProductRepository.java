package co.com.nequi.franchises.franchise.adapter.out.persistence.repositories;

import co.com.nequi.franchises.franchise.adapter.out.persistence.entitys.ProductEntity;
import co.com.nequi.franchises.franchise.adapter.out.persistence.reactive.ProductReactiveMysql;
import co.com.nequi.franchises.franchise.application.port.out.ProductPersistence;
import co.com.nequi.franchises.franchise.domain.module.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class ProductRepository implements ProductPersistence {

    private final ProductReactiveMysql productReactiveMysql;

    @Override
    public Mono<Product> save(Product product) {
        return this.productReactiveMysql.save(ProductEntity.entity(product))
            .map(ProductEntity::domain);
    }

    @Override
    public Mono<Boolean> updateName(Long productId, String name) {
        return this.productReactiveMysql.updateName(name, productId)
            .filter(value -> value == 1)
            .map(success -> Boolean.TRUE);
    }

    @Override
    public Mono<Boolean> updateStock(Long productId, Integer stock) {
        return this.productReactiveMysql.updateStock(stock, productId)
            .filter(value -> value == 1)
            .map(success -> Boolean.TRUE);
    }
}
