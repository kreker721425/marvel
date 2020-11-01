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
import com.github.kreker721425.marvel.service.impl.ComicBookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class)
public class TestComicBookService {

    private ComicBookRepository comicBookRepository;

    private ComicBookServiceImpl comicBookService;

    @Autowired
    private ComicBookMapper comicBookMapper;


    @BeforeEach
    void init() {
        comicBookRepository = mock(ComicBookRepository.class);
        comicBookService = new ComicBookServiceImpl(mock(CharacterRepository.class), comicBookRepository,
                mock(CharacterMapper.class), comicBookMapper);
    }

    @Test
    public void findAllTest() {
        when(comicBookRepository.findAll())
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), comicBookService.findAll());
    }

    @Test
    public void findByNameTest() {
        when(comicBookRepository.findByName(anyString()))
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), comicBookService.findByName("name"));
    }

    @Test
    public void findByWriterTest() {
        when(comicBookRepository.findByWriter(anyString()))
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), comicBookService.findByWriter("writer"));
    }

    @Test
    public void findByNameAndWriter() {
        when(comicBookRepository.findByNameAndWriter(anyString(), anyString()))
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), comicBookService.findByNameAndWriter("name", "writer"));
    }

    @Test
    public void sortByNameTest() {
        when(comicBookRepository.sortByName())
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), comicBookService.sortByName());
    }

    @Test
    public void sortByWriterTest() {
        when(comicBookRepository.sortByWriter())
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), comicBookService.sortByWriter());
    }

    @Test
    public void sortByPublishedTest() {
        when(comicBookRepository.sortByPublished())
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), comicBookService.sortByPublished());
    }

    @Test
    public void saveTest() {
        UUID id = UUID.randomUUID();
        ComicBookEntity entity = new ComicBookEntity();
        entity.setId(id);

        ComicBookDto dto = new ComicBookDto();
        dto.setId(id);

        when(comicBookRepository.save(entity))
                .thenReturn(entity);
        assertEquals(id, comicBookService.save(dto).getId());
    }

    @Test
    public void findComicsByCharacterTest() {
        CharacterEntity entity = new CharacterEntity();
        CharacterDto dto = new CharacterDto();
        when(comicBookRepository.findComicBookEntitiesByCharacters(entity))
                .thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), comicBookService.findComicsByCharacter(dto));
    }

}
