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
import com.github.kreker721425.marvel.service.ComicBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComicBookServiceImpl implements ComicBookService {

    private final CharacterRepository characterRepository;
    private final ComicBookRepository comicBookRepository;

    private final CharacterMapper characterMapper;
    private final ComicBookMapper comicBookMapper;

    @Override
    public Collection<ComicBookDto> findAll() {
        return comicBookMapper.toComicsDto(comicBookRepository.findAll());
    }

    @Override
    public Collection<ComicBookDto> findByName(String name) {
        return comicBookMapper.toComicsDto(comicBookRepository.findByName(name));
    }

    @Override
    public Collection<ComicBookDto> findByWriter(String writer) {
        return comicBookMapper.toComicsDto(comicBookRepository.findByWriter(writer));
    }

    @Override
    public Collection<ComicBookDto> findByPublished(Date published) {
        return comicBookMapper.toComicsDto(comicBookRepository.findByPublished(published));
    }

    @Override
    public Collection<ComicBookDto> sortByName() {
        return comicBookMapper.toComicsDto(comicBookRepository.sortByName());
    }

    @Override
    public Collection<ComicBookDto> sortByWriter() {
        return comicBookMapper.toComicsDto(comicBookRepository.sortByWriter());
    }

    @Override
    public Collection<ComicBookDto> sortByPublished() {
        return comicBookMapper.toComicsDto(comicBookRepository.sortByPublished());
    }

    @Override
    public ComicBookDto save(ComicBookDto comicBook) {
        if (comicBook.getId() == null)
            comicBook.setId(UUID.randomUUID());
        return comicBookMapper.toComicBookDto(comicBookRepository.save(comicBookMapper.toComicBookEntity(comicBook)));
    }

    @Override
    public void delete(ComicBookDto comicBook) {
        comicBookRepository.delete(comicBookMapper.toComicBookEntity(comicBook));
    }

    @Override
    public Collection<ComicBookDto> findComicsByCharacter(CharacterDto character) {
        return comicBookMapper.toComicsDto(comicBookRepository.findComicBookEntitiesByCharacters(characterMapper.toCharacterEntity(character)));
    }

    @Override
    public ComicBookDto findById(UUID id) {
        return comicBookMapper.toComicBookDto(comicBookRepository.findById(id).orElseThrow(ComicBookNotFoundException::new));
    }

    @Override
    public void update(ComicBookDto comicBookDto, UUID id) {
        ComicBookEntity comicBookEntity = comicBookMapper.toComicBookEntity(comicBookDto);
        comicBookEntity.setId(id);
        comicBookRepository.save(comicBookEntity);
    }


    @Override
    public void addCharacterForComicBook(UUID characterId, UUID comicBookId) {
        ComicBookEntity comicBook = comicBookRepository.findById(comicBookId).orElseThrow(ComicBookNotFoundException::new);
        Collection<CharacterEntity> collection = characterRepository.findCharacterEntitiesByComics(
                comicBookRepository.findById(comicBookId).orElseThrow(ComicBookNotFoundException::new)
        );
        CharacterEntity character = characterRepository.findById(characterId).orElseThrow(CharacterNotFoundException::new);

        if (collection.stream().anyMatch((c) -> c == character))
            return;

        collection.add(character);
        comicBook.setCharacters(collection);
        comicBookRepository.save(comicBook);
    }

    @Override
    public void deleteCharacterForComicBook(UUID characterId, UUID comicBookId) {
        ComicBookEntity comicBook = comicBookRepository.findById(comicBookId).orElseThrow(ComicBookNotFoundException::new);
        Collection<CharacterEntity> collection = characterRepository.findCharacterEntitiesByComics(
                comicBookRepository.findById(comicBookId).orElseThrow(ComicBookNotFoundException::new)
        );
        CharacterEntity character = characterRepository.findById(characterId).orElseThrow(CharacterNotFoundException::new);

        if (collection.stream().anyMatch((c) -> c == character)) {
            collection.remove(character);
            comicBook.setCharacters(collection);
            comicBookRepository.save(comicBook);
        }
    }

}
