package service;

import com.github.kreker721425.marvel.Application;
import com.github.kreker721425.marvel.dto.CharacterDto;
import com.github.kreker721425.marvel.dto.ComicBookDto;
import com.github.kreker721425.marvel.entity.CharacterEntity;
import com.github.kreker721425.marvel.entity.ComicBookEntity;
import com.github.kreker721425.marvel.mapper.CharacterMapper;
import com.github.kreker721425.marvel.mapper.ComicBookMapper;
import com.github.kreker721425.marvel.repository.CharacterRepository;
import com.github.kreker721425.marvel.repository.ComicBookRepository;
import com.github.kreker721425.marvel.service.impl.CharacterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class)
public class TestCharacterService {

    private CharacterRepository characterRepository;

    private CharacterServiceImpl characterService;

    @Autowired
    private CharacterMapper characterMapper;


    @BeforeEach
    void init() {
        characterRepository = mock(CharacterRepository.class);
        characterService = new CharacterServiceImpl(mock(ComicBookRepository.class), characterRepository,
                characterMapper, mock(ComicBookMapper.class));
    }

    @Test
    public void findAllTest() {
        when(characterRepository.findAll())
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), characterService.findAll());
    }

    @Test
    public void findByHeroNameTest() {
        when(characterRepository.findByHeroName(anyString()))
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), characterService.findByHeroName("heroName"));
    }

    @Test
    public void findByHumanNameTest() {
        when(characterRepository.findByHeroName(anyString()))
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), characterService.findByHeroName("humanName"));
    }

    @Test
    public void sortByHeroNameTest() {
        when(characterRepository.sortByHeroName())
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), characterService.sortByHeroName());
    }

    @Test
    public void sortByHumanNameTest() {
        when(characterRepository.sortByHumanName())
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), characterService.sortByHumanName());
    }

    @Test
    public void saveTest() {
        UUID id = UUID.randomUUID();
        CharacterEntity entity = new CharacterEntity();
        entity.setId(id);

        CharacterDto dto = new CharacterDto();
        dto.setId(id);

        when(characterRepository.save(entity))
                .thenReturn(entity);
        assertEquals(id, characterService.save(dto).getId());
    }

    @Test
    public void findCharactersByComicBookTest() {
        ComicBookEntity comicBookEntity = new ComicBookEntity();
        ComicBookDto comicBookDto = new ComicBookDto();
        when(characterRepository.findCharacterEntitiesByComics(comicBookEntity))
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), characterService.findCharactersByComicBook(comicBookDto));
    }

}
