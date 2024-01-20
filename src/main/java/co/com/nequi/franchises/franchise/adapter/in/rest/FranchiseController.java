package co.com.nequi.franchises.franchise.adapter.in.rest;

import co.com.nequi.franchises.franchise.application.port.in.ManageFranchiseUseCase;
import co.com.nequi.franchises.franchise.domain.module.Franchise;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("franchise")
@AllArgsConstructor
public class FranchiseController {

    private final ManageFranchiseUseCase manageFranchiseUseCase;
    @PostMapping
    public Mono<Franchise> create(@RequestParam("name") String name) {
        return this.manageFranchiseUseCase.create(name);
    }

    @PutMapping
    private Mono<Franchise> update(@RequestBody Franchise franchise) {
        return this.manageFranchiseUseCase.update(franchise);
    }

}
