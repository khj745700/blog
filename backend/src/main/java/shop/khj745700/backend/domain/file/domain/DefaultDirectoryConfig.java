package shop.khj745700.backend.domain.file.domain;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;


@RequiredArgsConstructor
@Configuration
public class DefaultDirectoryConfig {

    private final DirectoryRepository directoryRepository;

    @EventListener
    @Transactional
    public void saveDefaultDirectory(ApplicationReadyEvent event) {
        saveIfNotExist(1, "root", null);
        saveIfNotExist(2, "image", 1);
        saveIfNotExist(3, "profile", 2);
        saveIfNotExist(4, "compress", 3);
        saveIfNotExist(5, "original", 3);
        saveIfNotExist(6, "board", 2);
    }

    public void saveIfNotExist(Integer id, String name, Integer parentId) {
        if(directoryRepository.findById(id).isPresent()) {
            return;
        }
        Directory parent = parentId == null ? null : directoryRepository.getReferenceById(parentId);
        Directory directory = new Directory(parent, name);
        directoryRepository.save(directory);
    }
}
