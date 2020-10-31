package com.github.kreker721425.marvel.repository;

import com.github.kreker721425.marvel.entity.CharacterEntity;
import com.github.kreker721425.marvel.entity.ComicBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.UUID;

public interface CharacterRepository extends JpaRepository<CharacterEntity, UUID> {

    Collection<CharacterEntity> findCharacterEntitiesByComics(ComicBookEntity comic);

    Collection<CharacterEntity> findByHeroName(String heroName);

    Collection<CharacterEntity> findByHumanName(String humanName);

    @Query(value = "select * from character order by hero_name", nativeQuery = true)
    Collection<CharacterEntity> sortByHeroName();

    @Query(value = "select * from character order by human_name", nativeQuery = true)
    Collection<CharacterEntity> sortByHumanName();

}