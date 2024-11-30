package shop.khj745700.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private CustomExceptionDefinition definition;
    private Object id;

}
