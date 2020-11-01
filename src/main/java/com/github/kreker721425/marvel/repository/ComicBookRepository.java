package com.github.kreker721425.marvel.repository;

import com.github.kreker721425.marvel.entity.CharacterEntity;
import com.github.kreker721425.marvel.entity.ComicBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;


public interface ComicBookRepository extends JpaRepository<ComicBookEntity, UUID> {

    Collection<ComicBookEntity> findComicBookEntitiesByCharacters(CharacterEntity character);

    Collection<ComicBookEntity> findByName(String name);

    Collection<ComicBookEntity> findByWriter(String writer);

    Collection<ComicBookEntity> findByPublished(Date published);

    Collection<ComicBookEntity> findByNameAndWriter(String name, String writer);

    Collection<ComicBookEntity> findByNameAndPublished(String name, Date published);

    Collection<ComicBookEntity> findByWriterAndPublished(String writer, Date published);

    Collection<ComicBookEntity> findByNameAndWriterAndPublished(String name, String writer, Date published);

    @Query(value = "select * from comic_book order by name", nativeQuery = true)
    Collection<ComicBookEntity> sortByName();

    @Query(value = "select * from comic_book order by writer", nativeQuery = true)
    Collection<ComicBookEntity> sortByWriter();

    @Query(value = "select * from comic_book order by published", nativeQuery = true)
    Collection<ComicBookEntity> sortByPublished();
}
