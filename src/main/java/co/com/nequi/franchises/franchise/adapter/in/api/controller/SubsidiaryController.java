package co.com.nequi.franchises.franchise.adapter.in.api.controller;

import co.com.nequi.franchises.franchise.adapter.in.api.enums.Message;
import co.com.nequi.franchises.franchise.adapter.in.api.handler.Validation;
import co.com.nequi.franchises.franchise.adapter.in.api.request.SubsidiaryCreateRequest;
import co.com.nequi.franchises.franchise.adapter.in.api.request.SubsidiaryUpdateRequest;
import co.com.nequi.franchises.franchise.adapter.in.api.response.Response;
import co.com.nequi.franchises.franchise.application.port.in.ManageSubsidiaryUseCase;
import co.com.nequi.franchises.franchise.domain.module.Subsidiary;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("subsidiary")
@AllArgsConstructor
public class SubsidiaryController {

    private final ManageSubsidiaryUseCase manageSubsidiaryUseCase;
    private final Validation validation;

    @PostMapping
    public Mono<Response<Subsidiary>> create(@RequestBody SubsidiaryCreateRequest request) {
        return this.validation.validate(request)
            .flatMap(value -> this.manageSubsidiaryUseCase.create(SubsidiaryCreateRequest.domain(value)))
            .map(subsidiary ->
                Response.<Subsidiary>builder()
                    .code(HttpStatus.OK.value())
                    .message(Message.SUBSIDIARY_CREATED.getValue())
                    .data(subsidiary)
                    .build());
    }

    @PutMapping
    public Mono<Response<Boolean>> update(@RequestBody SubsidiaryUpdateRequest request) {
        return this.validation.validate(request)
            .flatMap(value -> this.manageSubsidiaryUseCase.updateName(value.getSubsidiaryId(), value.getName()))
            .map(success ->
                Response.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .message(Message.SUBSIDIARY_UPDATE.getValue())
                    .data(success)
                    .build());
    }


}
