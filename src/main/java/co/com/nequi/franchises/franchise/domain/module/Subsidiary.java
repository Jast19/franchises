package co.com.nequi.franchises.franchise.domain.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subsidiary {

    private Long id;
    private Long franchiseId;
    private String name;

}
