package co.com.nequi.franchises.domain;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import co.com.nequi.franchises.franchise.application.port.out.FranchisePersistence;
import co.com.nequi.franchises.franchise.application.service.ManageFranchiseService;
import co.com.nequi.franchises.franchise.domain.dto.ProductSubsidiary;
import co.com.nequi.franchises.franchise.domain.enums.TypeException;
import co.com.nequi.franchises.franchise.domain.exceptions.FranchiseException;
import co.com.nequi.franchises.franchise.domain.module.Franchise;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ManageFranchiseServiceTest {

    @Mock
    private FranchisePersistence franchisePersistence;

    @InjectMocks
    private ManageFranchiseService manageFranchiseService;

    @Test
    void testCreateFranchise() {
        String franchiseName = "Test Franchise";
        Franchise mockFranchise = new Franchise(1L, franchiseName);
        when(franchisePersistence.save(anyString())).thenReturn(Mono.just(mockFranchise));

        Mono<Franchise> result = manageFranchiseService.create(franchiseName);

        StepVerifier.create(result)
            .expectNext(mockFranchise)
            .verifyComplete();

        verify(franchisePersistence, times(1)).save(franchiseName);
    }

    @Test
    void testUpdateFranchise() {
        Long franchiseId = 1L;
        String franchiseName = "Updated Franchise";
        Franchise mockFranchise = new Franchise(franchiseId, franchiseName);

        when(franchisePersistence.update(anyLong(), anyString())).thenReturn(Mono.just(mockFranchise));
        Mono<Franchise> result = manageFranchiseService.update(franchiseId, franchiseName);

        StepVerifier.create(result)
            .expectNext(mockFranchise)
            .verifyComplete();

        verify(franchisePersistence, times(1)).update(franchiseId, franchiseName);
    }

    @Test
    void testUpdateFranchiseNotFound() {
        Long franchiseId = 1L;
        String franchiseName = "Updated Franchise";

        when(franchisePersistence.update(anyLong(), anyString())).thenReturn(Mono.error(TypeException.FRANCHISE_NO_FOUND.build()));
        Mono<Franchise> result = manageFranchiseService.update(franchiseId, franchiseName);

        StepVerifier.create(result)
            .expectErrorMatches(throwable ->
                throwable instanceof FranchiseException  fex &&
                    fex.getType() == TypeException.FRANCHISE_NO_FOUND)
            .verify();
    }

    @Test
    void testMaxStock() {
        Long franchiseId = 1L;
        List<ProductSubsidiary> mockProducts = Arrays.asList(
            new ProductSubsidiary(1L, 2l, "Product1", 500, "subsidiary2"),
            new ProductSubsidiary(2L, 2l, "Product2", 200, "subsidiary2"),
            new ProductSubsidiary(3L, 5l, "Product3", 400, "subsidiary5"),
            new ProductSubsidiary(4L, 5l, "Product4", 900, "subsidiary5")
        );

        when(franchisePersistence.maxStock(anyLong())).thenReturn(Flux.fromIterable(mockProducts));
        Mono<List<ProductSubsidiary>> result = manageFranchiseService.maxStock(franchiseId);

        StepVerifier.create(result)
            .expectNext(Arrays.asList(
                new ProductSubsidiary(1L, 2l, "Product1", 500, "subsidiary2"),
                new ProductSubsidiary(4L, 5l, "Product4", 900, "subsidiary5")
            ))
            .verifyComplete();

        verify(franchisePersistence, times(1)).maxStock(franchiseId);
    }
}
