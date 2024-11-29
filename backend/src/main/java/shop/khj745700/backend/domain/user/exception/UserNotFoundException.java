package shop.khj745700.backend.domain.user.exception;

import shop.khj745700.backend.global.exception.CustomException;
import shop.khj745700.backend.global.exception.CustomExceptionDefinition;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(UserExceptionConstants.USER_NOTFOUND_EXCEPTION);
    }
}
