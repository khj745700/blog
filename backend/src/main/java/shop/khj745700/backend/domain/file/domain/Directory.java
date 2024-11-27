package shop.khj745700.backend.domain.file.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "directory")
@Entity
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "directory_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_directory")
    private Directory parentDirectory;

    @Column(name = "directory_name")
    private String name;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentDirectory")
    private List<Directory> childDirectories;
}
