package shop.khj745700.backend.domain.board.exception;

import shop.khj745700.backend.global.exception.CustomException;

public class BoardNotFoundException extends CustomException {
    public BoardNotFoundException(Integer id) {
        super(BoardExceptionConstants.BOARD_NOT_FOUND, id);
    }
}
