package shop.khj745700.backend.domain.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.khj745700.backend.domain.file.domain.dao.FileStorer;

@RequiredArgsConstructor
@Service
public class FileSaveService {
    private final FileStorer fileStorer;


}
