package com.github.kreker721425.marvel.rest;

import com.github.kreker721425.marvel.dto.CharacterDto;
import com.github.kreker721425.marvel.dto.ComicBookDto;
import com.github.kreker721425.marvel.service.CharacterService;
import com.github.kreker721425.marvel.service.ComicBookService;
import com.github.kreker721425.marvel.service.FileService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comics")
public class ComicBookRestController {

    private final CharacterService characterService;
    private final ComicBookService comicBookService;
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<Collection<ComicBookDto>> getAll()
    {
        return ResponseEntity.ok(comicBookService.findAll());
    }


    @GetMapping(value = "/filer", params = {"name", "writer"})
    public Collection<ComicBookDto> searchByNameAndDescription(
            @RequestParam String name,
            @RequestParam String writer
    ) {

        if (!Strings.isNullOrEmpty(name)){
            if (!Strings.isNullOrEmpty(writer)){
                return comicBookService.findByNameAndWriter(name, writer);
            }
            return comicBookService.findByName(name);
        }
        if (!Strings.isNullOrEmpty(writer)){
            return comicBookService.findByWriter(writer);
        }

        return comicBookService.findAll();
    }

    @GetMapping(value = "/sort", params = "sort")
    public Collection<ComicBookDto> getSortedByName(@RequestParam String sort) {
        if (sort.equals("name")) {
            return comicBookService.sortByName();
        }
        if (sort.equals("writer")) {
            return comicBookService.sortByWriter();
        }
        return comicBookService.findAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ComicBookDto add(@RequestBody ComicBookDto comicBookDto, @RequestParam MultipartFile file) {
        comicBookDto.setImage(fileService.uploadFile(file));
        comicBookService.save(comicBookDto);
        return comicBookService.save(comicBookDto);
    }

    @GetMapping("/{comicBookId}")
    public ComicBookDto showCharacter(@PathVariable String comicBookId) {
        return comicBookService.findById(UUID.fromString(comicBookId));
    }

    @PutMapping("/{comicBookId}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ComicBookDto update(@PathVariable String comicBookId,
                                               @RequestBody ComicBookDto comicBookDto,
                                               @RequestParam MultipartFile file
    ) {
        comicBookDto.setImage(fileService.uploadFile(file));
        return comicBookService.update(comicBookDto,UUID.fromString(comicBookId));
    }

    @DeleteMapping("/{comicBookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String comicBookId) {
        comicBookService.delete(comicBookService.findById(UUID.fromString(comicBookId)));
    }

    @GetMapping("/{comicBookId}/characters")
    public Collection<CharacterDto> showComics (@PathVariable String comicBookId) {
        return characterService.findCharactersByComicBook(comicBookService.findById(UUID.fromString(comicBookId)));
    }

    @PostMapping("/{comicBookId}/characters/add")
    public Collection<CharacterDto> addComicBook(
            @PathVariable String comicBookId,
            @RequestBody Collection<CharacterDto> characterDtoCollection
    ) {
        for (CharacterDto characterDto: characterDtoCollection)
            comicBookService.addCharacterForComicBook(characterDto.getId(), UUID.fromString(comicBookId));
        return characterDtoCollection;
    }

    @DeleteMapping("/{comicBookId}/characters/{characterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComicBook(@PathVariable String characterId, @PathVariable String comicBookId) {
        comicBookService.deleteCharacterForComicBook(UUID.fromString(characterId), UUID.fromString(comicBookId));
    }

}
