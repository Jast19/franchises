package co.com.nequi.franchises.franchise.adapter.in.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {
    private int code;
    private String message;
    private T data;
    private String error;

}
