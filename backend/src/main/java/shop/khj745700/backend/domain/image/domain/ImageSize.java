package shop.khj745700.backend.domain.image.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ImageSize {
    SMALL(1280, 720), ORIGINAL(3820, 2160),
    ;

    private final int width;
    private final int height;
}
