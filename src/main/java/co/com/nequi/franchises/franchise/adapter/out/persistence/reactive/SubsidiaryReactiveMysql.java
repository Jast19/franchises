package co.com.nequi.franchises.franchise.adapter.out.persistence.reactive;

import co.com.nequi.franchises.franchise.adapter.out.persistence.entitys.SubsidiaryEntity;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SubsidiaryReactiveMysql extends ReactiveCrudRepository<SubsidiaryEntity, Long> {

    @Modifying
    @Query("UPDATE Subsidiary SET name = :name WHERE subsidiary_id = :subsidiaryId")
    Mono<Long> updateName(@Param("name") String name, @Param("subsidiaryId") Long subsidiaryId);

}
