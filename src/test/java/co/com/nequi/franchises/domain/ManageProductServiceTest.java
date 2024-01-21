package co.com.nequi.franchises.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import co.com.nequi.franchises.franchise.application.port.out.ProductPersistence;
import co.com.nequi.franchises.franchise.application.service.ManageProductService;
import co.com.nequi.franchises.franchise.domain.enums.TypeException;
import co.com.nequi.franchises.franchise.domain.exceptions.FranchiseException;
import co.com.nequi.franchises.franchise.domain.module.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ManageProductServiceTest {

    @Mock
    private ProductPersistence productPersistence;

    @InjectMocks
    private ManageProductService manageProductService;

    @Test
    void testCreateProduct() {
        Product mockProduct = new Product(1L, 1L, "Test Product", 100);
        Mockito.when(productPersistence.save(any())).thenReturn(Mono.just(mockProduct));

        Mono<Product> result = manageProductService.create(mockProduct);

        StepVerifier.create(result)
            .expectNext(mockProduct)
            .verifyComplete();

        Mockito.verify(productPersistence, times(1)).save(mockProduct);
    }

    @Test
    void testUpdateProductName() {
        Long productId = 1L;
        String updatedName = "Updated Product Name";

        when(productPersistence.updateName(anyLong(), anyString())).thenReturn(Mono.just(true));
        Mono<Boolean> result = manageProductService.updateName(productId, updatedName);

        StepVerifier.create(result)
            .expectNext(true)
            .verifyComplete();

        verify(productPersistence, times(1)).updateName(productId, updatedName);
    }

    @Test
    void testUpdateProductNameNotFound() {
        Long productId = 1L;
        String updatedName = "Updated Product Name";

        when(productPersistence.updateName(anyLong(), anyString())).thenReturn(Mono.empty());
        Mono<Boolean> result = manageProductService.updateName(productId, updatedName);

        StepVerifier.create(result)
            .expectErrorMatches(throwable ->
                throwable instanceof FranchiseException exception &&
                    exception.getType() == TypeException.PRODUCT_NO_FOUND)
            .verify();
    }

    @Test
    public void testUpdateProductStock() {
        // Arrange
        Long productId = 1L;
        Integer updatedStock = 200;

        when(productPersistence.updateStock(anyLong(), anyInt())).thenReturn(Mono.just(true));

        // Act
        Mono<Boolean> result = manageProductService.updateStock(productId, updatedStock);

        // Assert
        StepVerifier.create(result)
            .expectNext(true)
            .verifyComplete();

        verify(productPersistence, times(1)).updateStock(productId, updatedStock);
    }

    @Test
    void testUpdateProductStockNotFound() {
        Long productId = 1L;
        Integer updatedStock = 200;
        when(productPersistence.updateStock(anyLong(), anyInt())).thenReturn(Mono.empty());

        Mono<Boolean> result = manageProductService.updateStock(productId, updatedStock);

        StepVerifier.create(result)
            .expectErrorMatches(throwable ->
                throwable instanceof FranchiseException exception &&
                    exception.getType() == TypeException.PRODUCT_NO_FOUND)
            .verify();
    }
}
