package shop.khj745700.backend.domain.file.domain;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DefaultDirectory {
    ROOT(1), IMAGE(2), PROFILE(3), PROFILE_COMPRESS(4), PROFILE_ORIGINAL(5), BOARD(6),
    ;

    private final Integer id;

    public Directory getDirectory(EntityManager em) {
        return em.getReference(Directory.class, id);
    }
}