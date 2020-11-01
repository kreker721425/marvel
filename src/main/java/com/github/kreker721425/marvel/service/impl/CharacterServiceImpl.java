package com.github.kreker721425.marvel.service.impl;

import com.github.kreker721425.marvel.dto.CharacterDto;
import com.github.kreker721425.marvel.dto.ComicBookDto;
import com.github.kreker721425.marvel.entity.CharacterEntity;
import com.github.kreker721425.marvel.entity.ComicBookEntity;
import com.github.kreker721425.marvel.exception.CharacterNotFoundException;
import com.github.kreker721425.marvel.exception.ComicBookNotFoundException;
import com.github.kreker721425.marvel.mapper.CharacterMapper;
import com.github.kreker721425.marvel.mapper.ComicBookMapper;
import com.github.kreker721425.marvel.repository.CharacterRepository;
import com.github.kreker721425.marvel.repository.ComicBookRepository;
import com.github.kreker721425.marvel.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final ComicBookRepository comicBookRepository;
    private final CharacterRepository characterRepository;

    private final CharacterMapper characterMapper;
    private final ComicBookMapper comicBookMapper;

    @Override
    public Collection<CharacterDto> findAll() {
        return characterMapper.toCharacterDto(characterRepository.findAll());
    }

    @Override
    public Collection<CharacterDto> findByHeroName(String heroName) {
        return characterMapper.toCharacterDto(characterRepository.findByHeroName(heroName));
    }

    @Override
    public Collection<CharacterDto> findByHumanName(String humanName) {
        return characterMapper.toCharacterDto(characterRepository.findByHumanName(humanName));
    }

    @Override
    public Collection<CharacterEntity> findByHeroNameAndHumanName(String heroName, String humanName) {
        return characterRepository.findByHeroNameAndHumanName(heroName, humanName);
    }

    @Override
    public Collection<CharacterDto> sortByHeroName() {
        return characterMapper.toCharacterDto(characterRepository.sortByHeroName());
    }

    @Override
    public Collection<CharacterDto> sortByHumanName() {
        return characterMapper.toCharacterDto(characterRepository.sortByHumanName());
    }

    @Override
    public CharacterDto save(CharacterDto character) {
        if (character.getId() == null)
            character.setId(UUID.randomUUID());
        return characterMapper.toCharacterDto(characterRepository.save(characterMapper.toCharacterEntity(character)));
    }

    @Override
    public void delete(CharacterDto character) {
        characterRepository.delete(characterMapper.toCharacterEntity(character));
    }

    @Override
    public Collection<CharacterDto> findCharactersByComicBook(ComicBookDto comic) {
        return characterMapper.toCharacterDto(characterRepository.findCharacterEntitiesByComics(comicBookMapper.toComicBookEntity(comic)));
    }

    @Override
    public CharacterDto findById(UUID id) {
        return characterMapper.toCharacterDto(characterRepository.findById(id).orElseThrow(CharacterNotFoundException::new));
    }

    @Override
    public void update(CharacterDto characterDto, UUID id) {
        CharacterEntity characterEntity = characterMapper.toCharacterEntity(characterDto);
        characterEntity.setId(id);
        characterRepository.save(characterEntity);
    }

    @Override
    public void addComicBookForCharacter(UUID characterId, UUID comicBookId) {
        CharacterEntity character = characterRepository.findById(characterId).orElseThrow(CharacterNotFoundException::new);
        Collection<ComicBookEntity> collection = comicBookRepository.findComicBookEntitiesByCharacters(
                characterRepository.findById(characterId).orElseThrow(CharacterNotFoundException::new)
        );
        ComicBookEntity comicBook = comicBookRepository.findById(comicBookId).orElseThrow(ComicBookNotFoundException::new);

        if (collection.stream().anyMatch((c) -> c == comicBook))
            return;

        collection.add(comicBook);
        character.setComics(collection);
        characterRepository.save(character);
    }

    @Override
    public void deleteComicBookForCharacter(UUID characterId, UUID comicBookId) {
        CharacterEntity character = characterRepository.findById(characterId).orElseThrow(CharacterNotFoundException::new);
        Collection<ComicBookEntity> collection = comicBookRepository.findComicBookEntitiesByCharacters(
                characterRepository.findById(characterId).orElseThrow(CharacterNotFoundException::new)
        );
        ComicBookEntity comicBook = comicBookRepository.findById(comicBookId).orElseThrow(ComicBookNotFoundException::new);

        if (collection.stream().anyMatch((c) -> c == comicBook)) {
            collection.remove(comicBook);
            character.setComics(collection);
            characterRepository.save(character);
        }
    }


}
