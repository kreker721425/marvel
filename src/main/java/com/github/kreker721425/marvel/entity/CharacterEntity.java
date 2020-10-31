package com.github.kreker721425.marvel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "character")
public class CharacterEntity {

    @Id
    private UUID id;

    private String heroName;
    private String humanName;
    private String description;
    private String image;

    @ManyToMany
    @JoinTable(name = "character_comic_book",
            joinColumns = @JoinColumn(name = "id_character"),
            inverseJoinColumns = @JoinColumn(name = "id_comic_book"))
    private Collection<ComicBookEntity> comics;
}
