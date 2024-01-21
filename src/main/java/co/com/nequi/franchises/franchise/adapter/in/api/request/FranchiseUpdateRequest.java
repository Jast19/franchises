package co.com.nequi.franchises.franchise.adapter.in.api.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseUpdateRequest {

    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    private String name;
}
