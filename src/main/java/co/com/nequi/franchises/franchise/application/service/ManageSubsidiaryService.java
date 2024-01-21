package co.com.nequi.franchises.franchise.application.service;

import co.com.nequi.franchises.franchise.application.port.in.ManageSubsidiaryUseCase;
import co.com.nequi.franchises.franchise.application.port.out.SubsidiaryPersistence;
import co.com.nequi.franchises.franchise.domain.enums.TypeException;
import co.com.nequi.franchises.franchise.domain.module.Subsidiary;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ManageSubsidiaryService implements ManageSubsidiaryUseCase {

    private final SubsidiaryPersistence subsidiaryPersistence;

    @Override
    public Mono<Subsidiary> create(Subsidiary subsidiary) {
        return this.subsidiaryPersistence.save(subsidiary);
    }

    @Override
    public Mono<Boolean> updateName(Long subsidiaryId, String name) {
        return this.subsidiaryPersistence.updateName(subsidiaryId, name)
            .switchIfEmpty(Mono.error(TypeException.SUBSIDIARY_NO_FOUND.build(subsidiaryId.toString())));
    }
}
