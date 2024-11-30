package shop.khj745700.backend.domain.file.exception;

import shop.khj745700.backend.global.exception.CustomException;

public class DirectoryNotFoundException extends CustomException {
    public DirectoryNotFoundException(Integer id) {
        super(FileExceptionConstants.DIRECTORY_NOT_FOUND, id);
    }
}
