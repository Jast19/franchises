package co.com.nequi.franchises.franchise.application.port.out;

import co.com.nequi.franchises.franchise.domain.module.Subsidiary;
import reactor.core.publisher.Mono;

public interface SubsidiaryPersistence {

    Mono<Subsidiary> save(Subsidiary subsidiary);
    Mono<Boolean> updateName(Long subsidiaryId, String name);

}
