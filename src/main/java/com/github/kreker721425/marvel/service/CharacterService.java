package com.github.kreker721425.marvel.service;

import com.github.kreker721425.marvel.dto.CharacterDto;
import com.github.kreker721425.marvel.dto.ComicBookDto;

import java.util.Collection;
import java.util.UUID;

public interface CharacterService {

    Collection<CharacterDto> findAll();

    Collection<CharacterDto> findByName(String name);

    void save(CharacterDto characterDto);

    void delete(CharacterDto characterDto);

    Collection<CharacterDto> findCharactersByComicBook(ComicBookDto comicBookDto);

    CharacterDto findById(UUID id);

    void update(CharacterDto characterDto, UUID id);

    void addComicBookForCharacter(UUID characterId, UUID comicBookId);

    void deleteComicBookForCharacter(UUID characterId, UUID comicBookId);
}