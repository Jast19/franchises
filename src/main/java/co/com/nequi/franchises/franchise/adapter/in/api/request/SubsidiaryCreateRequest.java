package co.com.nequi.franchises.franchise.adapter.in.api.request;

import co.com.nequi.franchises.franchise.domain.module.Subsidiary;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubsidiaryCreateRequest {

    @NotNull
    private Long franchiseId;

    @NotNull
    @NotEmpty
    private String name;

    public static Subsidiary domain(SubsidiaryCreateRequest request) {
        return new Subsidiary(null, request.getFranchiseId(), request.getName());
    }

}
