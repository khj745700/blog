package shop.khj745700.backend.domain.image.domain;

import jakarta.persistence.*;
import lombok.*;
import shop.khj745700.backend.domain.file.domain.File;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "compressed_image")
@Entity
public class CompressedImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compressed_image_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", nullable = false)
    private File file;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private ImageSize size;

    @Builder(builderMethodName = "createCompressedImageBuilder")
    public CompressedImage(Image image, File file, ImageSize size) {
        this.image = image;
        this.file = file;
        this.size = size;
    }
}
