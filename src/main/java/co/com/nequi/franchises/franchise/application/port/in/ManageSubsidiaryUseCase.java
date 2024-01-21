package co.com.nequi.franchises.franchise.application.port.in;

import co.com.nequi.franchises.franchise.domain.module.Subsidiary;
import reactor.core.publisher.Mono;

public interface ManageSubsidiaryUseCase {

    Mono<Subsidiary> create(Subsidiary subsidiary);

    Mono<Boolean> updateName(Long subsidiaryId, String name);

}
