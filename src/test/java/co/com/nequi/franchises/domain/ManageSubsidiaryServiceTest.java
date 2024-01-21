package co.com.nequi.franchises.domain;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import co.com.nequi.franchises.franchise.application.port.out.SubsidiaryPersistence;
import co.com.nequi.franchises.franchise.application.service.ManageSubsidiaryService;
import co.com.nequi.franchises.franchise.domain.enums.TypeException;
import co.com.nequi.franchises.franchise.domain.exceptions.FranchiseException;
import co.com.nequi.franchises.franchise.domain.module.Subsidiary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ManageSubsidiaryServiceTest {


    @Mock
    private SubsidiaryPersistence subsidiaryPersistence;

    @InjectMocks
    private ManageSubsidiaryService manageSubsidiaryService;

    @Test
    void testCreateSubsidiary() {
        Subsidiary mockSubsidiary = new Subsidiary(1L, 1L, "Test Subsidiary");
        when(subsidiaryPersistence.save(any())).thenReturn(Mono.just(mockSubsidiary));

        Mono<Subsidiary> result = manageSubsidiaryService.create(mockSubsidiary);

        StepVerifier.create(result)
            .expectNext(mockSubsidiary)
            .verifyComplete();

        verify(subsidiaryPersistence, times(1)).save(mockSubsidiary);
    }

    @Test
    void testUpdateSubsidiaryName() {
        Long subsidiaryId = 1L;
        String updatedName = "Updated Subsidiary Name";

        when(subsidiaryPersistence.updateName(anyLong(), anyString())).thenReturn(Mono.just(true));
        Mono<Boolean> result = manageSubsidiaryService.updateName(subsidiaryId, updatedName);

        StepVerifier.create(result)
            .expectNext(true)
            .verifyComplete();

        verify(subsidiaryPersistence, times(1)).updateName(subsidiaryId, updatedName);
    }

    @Test
    void testUpdateSubsidiaryNameNotFound() {
        Long subsidiaryId = 1L;
        String updatedName = "Updated Subsidiary Name";

        when(subsidiaryPersistence.updateName(anyLong(), anyString())).thenReturn(Mono.empty());

        Mono<Boolean> result = manageSubsidiaryService.updateName(subsidiaryId, updatedName);

        StepVerifier.create(result)
            .expectErrorMatches(throwable ->
                throwable instanceof FranchiseException exception &&
                    exception.getType() == TypeException.SUBSIDIARY_NO_FOUND)
            .verify();
    }

}
