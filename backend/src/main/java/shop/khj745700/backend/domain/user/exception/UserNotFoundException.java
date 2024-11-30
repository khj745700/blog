package shop.khj745700.backend.domain.user.exception;

import shop.khj745700.backend.global.exception.CustomException;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(Integer id) {
        super(UserExceptionConstants.USER_NOTFOUND_EXCEPTION, id);
    }
}
