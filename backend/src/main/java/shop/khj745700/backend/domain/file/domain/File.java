package shop.khj745700.backend.domain.file.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "file")
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer id;

    @Column(name = "file_name")
    private String name;

    @Column(name = "file_extension")
    private String extension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directory_id")
    private Directory directory;
}
