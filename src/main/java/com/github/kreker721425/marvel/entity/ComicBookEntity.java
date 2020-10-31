package com.github.kreker721425.marvel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ComicBookEntity {

    @Id
    private UUID id;
    private String name;
    private String description;
    private String image;

    @ManyToMany(mappedBy = "character_entity",fetch = FetchType.LAZY)
    private Collection<CharacterEntity> characters;
}
