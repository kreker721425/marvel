package com.github.kreker721425.marvel.service;

import com.github.kreker721425.marvel.dto.CharacterDto;
import com.github.kreker721425.marvel.dto.ComicBookDto;

import java.util.Collection;
import java.util.UUID;

public interface ComicBookService {

    Collection<ComicBookDto> findAll();

    void save(ComicBookDto comicBook);

    void delete(ComicBookDto comicBook);

    Collection<ComicBookDto> findComicsByCharacter(CharacterDto character);

    ComicBookDto findById(UUID id);

    void update(ComicBookDto comicBookDto, UUID id);

    void addCharacterForComicBook(UUID characterId, UUID comicBookId);
}
