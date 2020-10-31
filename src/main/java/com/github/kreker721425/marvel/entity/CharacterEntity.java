package com.github.kreker721425.marvel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterEntity {

    @Id
    private UUID id;
    private String name;
    private String description;
    private String image;

    @ManyToMany(mappedBy = "comic_book_entity",fetch = FetchType.LAZY)
    private Collection<ComicBookEntity> comics;
}
