package shop.khj745700.backend.domain.board.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import shop.khj745700.backend.global.exception.CustomExceptionDefinition;

@RequiredArgsConstructor
@Getter
public enum BoardExceptionConstants implements CustomExceptionDefinition {
    BOARD_NOT_FOUND("게시글을 찾을 수 없습니다.", "B001", HttpStatus.NOT_FOUND)
    ;

    private final String message;
    private final String code;
    private final HttpStatus statusCode;

}
