package com.github.kreker721425.marvel.repository;

import com.github.kreker721425.marvel.entity.CharacterEntity;
import com.github.kreker721425.marvel.entity.ComicBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface CharacterRepository extends JpaRepository<CharacterEntity, UUID> {

    Collection<CharacterEntity> findCharacterEntitiesByComics(ComicBookEntity comic);

    Collection<CharacterEntity> findByName(String name);

}