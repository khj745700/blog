package shop.khj745700.backend.domain.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import shop.khj745700.backend.global.exception.CustomExceptionDefinition;

@RequiredArgsConstructor
@Getter
public enum UserExceptionConstants implements CustomExceptionDefinition {
    USER_NOTFOUND_EXCEPTION("사용자를 찾을 수 없습니다.", "U001", HttpStatus.NOT_FOUND);

    private final String message;
    private final String code;
    private final HttpStatus statusCode;

}
