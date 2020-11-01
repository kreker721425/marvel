package com.github.kreker721425.marvel.rest;

import com.github.kreker721425.marvel.dto.CharacterDto;
import com.github.kreker721425.marvel.dto.ComicBookDto;
import com.github.kreker721425.marvel.exception.CharacterNotFoundException;
import com.github.kreker721425.marvel.service.CharacterService;
import com.github.kreker721425.marvel.service.ComicBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comics")
public class ComicBookRestController {

    private final CharacterService characterService;
    private final ComicBookService comicBookService;

    @GetMapping
    public ResponseEntity<Collection<ComicBookDto>> getAll()
    {
        return ResponseEntity.ok(comicBookService.findAll());
    }


    @GetMapping(value = "/filer", params = {"name", "writer"})
    public ResponseEntity<Collection<ComicBookDto>> searchByNameAndDescription(
            @RequestParam String name,
            @RequestParam String writer
    ) {

        if (name != null && !name.isEmpty()){
            if (writer != null && !writer.isEmpty()){
                return ResponseEntity.ok(comicBookService.findByNameAndWriter(name, writer));
            }
            return ResponseEntity.ok(comicBookService.findByName(name));
        }
        if (writer != null && !writer.isEmpty()){
            return ResponseEntity.ok(comicBookService.findByWriter(writer));
        }

        return ResponseEntity.ok(comicBookService.findAll());
    }

    @GetMapping(value = "/sort", params = "f")
    public ResponseEntity<Collection<ComicBookDto>> getSortedByName(@RequestParam String sort) {
        if (sort.equals("name")) {
            return ResponseEntity.ok(comicBookService.sortByName());
        }
        if (sort.equals("writer")) {
            return ResponseEntity.ok(comicBookService.sortByWriter());
        }
        return ResponseEntity.ok(comicBookService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<ComicBookDto> add(@RequestBody ComicBookDto comicBookDto) {
        comicBookService.save(comicBookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comicBookDto);
    }

    @GetMapping("/{comicBookId}")
    public ResponseEntity<ComicBookDto> showCharacter(@PathVariable UUID comicBookId) {
        try{
            return ResponseEntity.ok(comicBookService.findById(comicBookId));
        }
        catch (CharacterNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{comicBookId}/update")
    public ResponseEntity<ComicBookDto> update(@PathVariable UUID comicBookId, @RequestBody ComicBookDto comicBookDto) {
        comicBookService.update(comicBookDto,comicBookId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comicBookDto);
    }

    @DeleteMapping("/{comicBookId}")
    public void delete(@PathVariable UUID comicBookId) {
        comicBookService.delete(comicBookService.findById(comicBookId));
    }

    @GetMapping("/{comicBookId}/characters")
    public ResponseEntity<Collection<CharacterDto>> showComics (@PathVariable UUID comicBookId) {
        return ResponseEntity.ok(characterService.findCharactersByComicBook(comicBookService.findById(comicBookId)));
    }

    @PostMapping("/{comicBookId}/characters/add")
    public ResponseEntity<Collection<CharacterDto>> addComicBook(
            @PathVariable UUID comicBookId,
            @RequestBody Collection<CharacterDto> characterDtoCollection
    ) {
        for (CharacterDto characterDto: characterDtoCollection)
            comicBookService.addCharacterForComicBook(characterDto.getId(), comicBookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterDtoCollection);
    }

    @DeleteMapping("/{comicBookId}/characters/{characterId}")
    public void deleteComicBook(@PathVariable UUID characterId, @PathVariable UUID comicBookId) {
        comicBookService.deleteCharacterForComicBook(characterId, comicBookId);
    }

}
