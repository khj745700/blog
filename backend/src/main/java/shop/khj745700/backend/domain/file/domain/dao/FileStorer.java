package shop.khj745700.backend.domain.file.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.khj745700.backend.domain.file.domain.Directory;
import shop.khj745700.backend.domain.file.domain.File;
import shop.khj745700.backend.domain.file.domain.FileRepository;



@RequiredArgsConstructor
@Repository
public class FileStorer {
    private final FileRepository fileRepository;

    @Transactional
    public File store(String extension, Directory directory, String filename) {
        return fileRepository.save(File.createNewFile().directory(directory).extension(extension).name(filename).build());
    }
}
