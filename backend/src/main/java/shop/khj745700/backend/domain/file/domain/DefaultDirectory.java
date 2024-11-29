package shop.khj745700.backend.domain.file.domain;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DefaultDirectory {
    ROOT(new Directory(1, null, "")),
    IMAGE(new Directory(1, null, "images")),
    PROFILE(new Directory(2, IMAGE.directory, "profile")),
    ;

    private final Directory directory;
}
