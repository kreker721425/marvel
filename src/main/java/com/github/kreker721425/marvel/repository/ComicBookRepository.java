package com.github.kreker721425.marvel.repository;

import com.github.kreker721425.marvel.entity.CharacterEntity;
import com.github.kreker721425.marvel.entity.ComicBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;


public interface ComicBookRepository extends JpaRepository<ComicBookEntity, UUID> {

    Collection<ComicBookEntity> findComicBookEntitiesByCharacters(CharacterEntity character);
}
