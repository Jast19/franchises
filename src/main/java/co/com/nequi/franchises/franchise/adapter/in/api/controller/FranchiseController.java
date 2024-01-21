package co.com.nequi.franchises.franchise.adapter.in.api.controller;

import co.com.nequi.franchises.franchise.adapter.in.api.enums.Message;
import co.com.nequi.franchises.franchise.adapter.in.api.handler.Validation;
import co.com.nequi.franchises.franchise.adapter.in.api.request.FranchiseUpdateRequest;
import co.com.nequi.franchises.franchise.adapter.in.api.response.Response;
import co.com.nequi.franchises.franchise.application.port.in.ManageFranchiseUseCase;
import co.com.nequi.franchises.franchise.domain.dto.ProductSubsidiary;
import co.com.nequi.franchises.franchise.domain.module.Franchise;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("franchise")
@AllArgsConstructor
public class FranchiseController {

    private final ManageFranchiseUseCase manageFranchiseUseCase;
    private final Validation validation;

    @PostMapping
    public Mono<Response<Franchise>> create(@RequestParam("name") String name) {
        return this.validation.requireNonNull(name)
            .flatMap(this.manageFranchiseUseCase::create)
            .map(franchise ->
                Response.<Franchise>builder()
                    .code(HttpStatus.OK.value())
                    .message(Message.FRANCHISE_CREATED.getValue())
                    .data(franchise)
                    .build());
    }

    @PutMapping
    public Mono<Response<String>> update(@RequestBody FranchiseUpdateRequest request) {
        return this.validation.validate(request)
            .flatMap(value -> this.manageFranchiseUseCase.update(value.getId(), value.getName()))
            .map(success -> Response.<String>builder()
                .code(HttpStatus.OK.value())
                .message(Message.FRANCHISE_UPDATE.getValue())
                .data(Message.FRANCHISE_UPDATE.getValue())
                .build());
    }

    @GetMapping("/{franchiseId}/maxStock")
    public Mono<Response<List<ProductSubsidiary>>> maxStock(@PathVariable("franchiseId") Long franchiseId) {
        return this.manageFranchiseUseCase.maxStock(franchiseId)
            .map(product ->
                Response.<List<ProductSubsidiary>>builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.name())
                    .data(product)
                    .build());
    }

}
