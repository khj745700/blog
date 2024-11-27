package shop.khj745700.backend.domain.image.domain;


import jakarta.persistence.*;
import lombok.*;
import shop.khj745700.backend.domain.file.domain.File;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "image")
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;


}
