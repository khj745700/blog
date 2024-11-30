package shop.khj745700.backend.domain.file.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import shop.khj745700.backend.global.exception.CustomExceptionDefinition;

@RequiredArgsConstructor
@Getter
public enum FileExceptionConstants implements CustomExceptionDefinition {
    DIRECTORY_NOT_FOUND("디렉토리를 찾을 수 없습니다.", HttpStatus.NOT_FOUND, "D001"),
    ;

    private final String message;
    private final HttpStatus statusCode;
    private final String code;

}
