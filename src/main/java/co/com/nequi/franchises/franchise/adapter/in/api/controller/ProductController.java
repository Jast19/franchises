package co.com.nequi.franchises.franchise.adapter.in.api.controller;

import co.com.nequi.franchises.franchise.adapter.in.api.enums.Message;
import co.com.nequi.franchises.franchise.adapter.in.api.handler.Validation;
import co.com.nequi.franchises.franchise.adapter.in.api.request.ProductCreateRequest;
import co.com.nequi.franchises.franchise.adapter.in.api.request.ProductUpdateRequest;
import co.com.nequi.franchises.franchise.adapter.in.api.request.ProductUpdateStockRequest;
import co.com.nequi.franchises.franchise.adapter.in.api.response.Response;
import co.com.nequi.franchises.franchise.application.port.in.ManageProductUseCase;
import co.com.nequi.franchises.franchise.domain.dto.ProductSubsidiary;
import co.com.nequi.franchises.franchise.domain.module.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
@AllArgsConstructor
public class ProductController {

    private final ManageProductUseCase manageProductUseCase;
    private final Validation validation;

    @PostMapping
    public Mono<Response<Product>> create(@RequestBody ProductCreateRequest request) {
        return this.validation.validate(request)
            .flatMap(value -> this.manageProductUseCase.create(ProductCreateRequest.domain(value)))
            .map(product ->
                Response.<Product>builder()
                    .code(HttpStatus.OK.value())
                    .message(Message.PRODUCT_CREATED.getValue())
                    .data(product)
                    .build());
    }

    @PutMapping
    public Mono<Response<Boolean>> updateName(@RequestBody ProductUpdateRequest request) {
        return this.validation.validate(request)
            .flatMap(value -> this.manageProductUseCase.updateName(value.getProductId(), value.getName()))
            .map(product ->
                Response.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .message(Message.PRODUCT_UPDATE.getValue())
                    .data(product)
                    .build());
    }

    @PutMapping("/stock")
    public Mono<Response<Boolean>> updateStock(@RequestBody ProductUpdateStockRequest request) {
        return this.validation.validate(request)
            .flatMap(value -> this.manageProductUseCase.updateStock(value.getProductId(), value.getStock()))
            .map(product ->
                Response.<Boolean>builder()
                    .code(HttpStatus.OK.value())
                    .message(Message.PRODUCT_UPDATE.getValue())
                    .data(product)
                    .build());
    }

}
