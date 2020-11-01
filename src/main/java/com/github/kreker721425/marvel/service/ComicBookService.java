package com.github.kreker721425.marvel.service;

import com.github.kreker721425.marvel.dto.CharacterDto;
import com.github.kreker721425.marvel.dto.ComicBookDto;
import com.github.kreker721425.marvel.entity.ComicBookEntity;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public interface ComicBookService {

    Collection<ComicBookDto> findAll();

    Collection<ComicBookDto> findByName(String name);

    Collection<ComicBookDto> findByWriter(String writer);

    Collection<ComicBookDto> findByPublished(Date published);

    Collection<ComicBookDto> findByNameAndWriter(String name, String writer);

    Collection<ComicBookDto> findByNameAndPublished(String name, Date published);

    Collection<ComicBookDto> findByWriterAndPublished(String writer, Date published);

    Collection<ComicBookDto> findByNameAndWriterAndPublished(String name, String writer, Date published);

    Collection<ComicBookDto> sortByName();

    Collection<ComicBookDto> sortByWriter();

    Collection<ComicBookDto> sortByPublished();

    ComicBookDto save(ComicBookDto comicBook);

    void delete(ComicBookDto comicBook);

    Collection<ComicBookDto> findComicsByCharacter(CharacterDto character);

    ComicBookDto findById(UUID id);

    void update(ComicBookDto comicBookDto, UUID id);

    void addCharacterForComicBook(UUID characterId, UUID comicBookId);

    void deleteCharacterForComicBook(UUID characterId, UUID comicBookId);
}
