package com.github.kreker721425.marvel.service;

import com.github.kreker721425.marvel.dto.CharacterDto;
import com.github.kreker721425.marvel.dto.ComicBookDto;
import com.github.kreker721425.marvel.entity.CharacterEntity;

import java.util.Collection;
import java.util.UUID;

public interface CharacterService {

    Collection<CharacterDto> findAll();

    Collection<CharacterDto> findByHeroName(String heroName);

    Collection<CharacterDto> findByHumanName(String humanName);

    Collection<CharacterDto> findByHeroNameAndHumanName(String heroName, String humanName);

    Collection<CharacterDto> sortByHeroName();

    Collection<CharacterDto> sortByHumanName();

    CharacterDto save(CharacterDto characterDto);

    void delete(CharacterDto characterDto);

    Collection<CharacterDto> findCharactersByComicBook(ComicBookDto comicBookDto);

    CharacterDto findById(UUID id);

    CharacterDto update(CharacterDto characterDto, UUID id);

    void addComicBookForCharacter(UUID characterId, UUID comicBookId);

    void deleteComicBookForCharacter(UUID characterId, UUID comicBookId);
}
