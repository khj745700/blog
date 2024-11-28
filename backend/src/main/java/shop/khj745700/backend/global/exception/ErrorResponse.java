package shop.khj745700.backend.global.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ErrorResponse {
    String message;
    String code;
}
