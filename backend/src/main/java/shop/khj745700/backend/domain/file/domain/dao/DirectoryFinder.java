package shop.khj745700.backend.domain.file.domain.dao;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.khj745700.backend.domain.file.domain.DefaultDirectory;
import shop.khj745700.backend.domain.file.domain.Directory;
import shop.khj745700.backend.domain.file.domain.DirectoryRepository;
import shop.khj745700.backend.domain.file.exception.DirectoryNotFoundException;

import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class DirectoryFinder {
    private final DirectoryRepository directoryRepository;
    private final EntityManager entityManager;

    @Transactional(readOnly = true)
    public Directory read(Integer id) {
        if(id == null) { return null; }
        return directoryRepository.getReferenceById(id);
    }


    @Transactional(readOnly = true)
    public Directory surelyFindById(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        return directoryRepository.findById(id).orElseThrow(() -> new DirectoryNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Directory getDefaultDirectory(DefaultDirectory defaultDirectory) {
        Objects.requireNonNull(defaultDirectory, "directory must not be null");
        return defaultDirectory.getDirectory(entityManager);
    }
}
