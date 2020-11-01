package com.github.kreker721425.marvel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comic_book")
public class ComicBookEntity {

    @Id
    private UUID id;

    private String name;
    private String writer;
    private String description;
    private String image;

    @ManyToMany
    @JoinTable(name = "character_comic_book",
            joinColumns = @JoinColumn(name = "id_comic_book"),
            inverseJoinColumns = @JoinColumn(name = "id_character"))
    private Collection<CharacterEntity> characters;
}
