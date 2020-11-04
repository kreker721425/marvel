package com.github.kreker721425.marvel.rest;

import com.github.kreker721425.marvel.dto.CharacterDto;
import com.github.kreker721425.marvel.dto.ComicBookDto;
import com.github.kreker721425.marvel.service.CharacterService;
import com.github.kreker721425.marvel.service.ComicBookService;
import com.github.kreker721425.marvel.service.FileService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterRestController {

    private final CharacterService characterService;
    private final ComicBookService comicBookService;
    private final FileService fileService;

    @GetMapping
    public Collection<CharacterDto> getAll()
    {
        return characterService.findAll();
    }


    @GetMapping(value = "/filer", params = {"heroName", "humanName"})
    public Collection<CharacterDto> searchByNameAndDescription(
            @RequestParam String heroName,
            @RequestParam String humanName
    ) {

        if (!Strings.isNullOrEmpty(heroName)){
            if (!Strings.isNullOrEmpty(humanName)){
                return characterService.findByHeroNameAndHumanName(heroName, humanName);
            }
            return characterService.findByHeroName(heroName);
        }
        if (!Strings.isNullOrEmpty(humanName)){
            return characterService.findByHumanName(humanName);
        }
        return characterService.findAll();
    }

    @GetMapping(value = "/sort", params = "sort")
    public Collection<CharacterDto> getSortedByName(@RequestParam String sort) {
        if (sort.equals("heroName")) {
            return characterService.sortByHeroName();
        }
        if (sort.equals("humanName")) {
            return characterService.sortByHumanName();
        }
        return characterService.findAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterDto add(@RequestBody CharacterDto characterDto, @RequestParam MultipartFile file) {
        characterDto.setImage(fileService.uploadFile(file));
        return characterService.save(characterDto);
    }

    @GetMapping("/{characterId}")
    public CharacterDto showCharacter(@PathVariable String characterId) {
        return characterService.findById(UUID.fromString(characterId));
    }

    @PutMapping("/{characterId}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CharacterDto update(@PathVariable String characterId,
                                               @RequestBody CharacterDto characterDto,
                                               @RequestParam MultipartFile file
    ) {
        characterDto.setImage(fileService.uploadFile(file));
        return characterService.update(characterDto,UUID.fromString(characterId));
    }

    @DeleteMapping("/{characterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String characterId) {
        characterService.delete(characterService.findById(UUID.fromString(characterId)));
    }

    @GetMapping("/{characterId}/comics")
    public Collection<ComicBookDto> showComics (@PathVariable String characterId) {
        return comicBookService.findComicsByCharacter(characterService.findById(UUID.fromString(characterId)));
    }

    @PostMapping("/{characterId}/comics/add")
    public Collection<ComicBookDto> addComicBook(
            @PathVariable String characterId,
            @RequestBody Collection<ComicBookDto> comicBookDtoCollection
    ) {
        for (ComicBookDto comicBookDto: comicBookDtoCollection)
            characterService.addComicBookForCharacter(UUID.fromString(characterId), comicBookDto.getId());
        return comicBookDtoCollection;
    }

    @DeleteMapping("/{characterId}/comics/{comicBookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComicBook(@PathVariable String characterId, @PathVariable String comicBookId) {
        characterService.deleteComicBookForCharacter(UUID.fromString(characterId), UUID.fromString(comicBookId));
    }

}