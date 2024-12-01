package shop.khj745700.backend.domain.image.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "image")
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer id;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompressedImage> sizes = new ArrayList<>();

    public Image() {
        this.sizes = new ArrayList<>();
    }

    public void addImage(CompressedImage image) {
        sizes.add(image);
    }
}
