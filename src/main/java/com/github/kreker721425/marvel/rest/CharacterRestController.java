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
@RequestMapping("/characters")
public class CharacterRestController {

    private final CharacterService characterService;
    private final ComicBookService comicBookService;

    @GetMapping
    public ResponseEntity<Collection<CharacterDto>> getAll()
    {
        return ResponseEntity.ok(characterService.findAll());
    }


    @GetMapping(value = "/filer", params = {"heroName", "humanName"})
    public ResponseEntity<Collection<CharacterDto>> searchByNameAndDescription(
            @RequestParam String heroName,
            @RequestParam String humanName
    ) {

        if (heroName != null && !heroName.isEmpty()){
            if (humanName != null && !humanName.isEmpty()){
                return ResponseEntity.ok(characterService.findByHeroNameAndHumanName(heroName, humanName));
            }
            return ResponseEntity.ok(characterService.findByHeroName(heroName));
        }
        if (humanName != null && !humanName.isEmpty()){
            return ResponseEntity.ok(characterService.findByHumanName(humanName));
        }

        return ResponseEntity.ok(characterService.findAll());
    }

    @GetMapping(value = "/sort", params = "f")
    public ResponseEntity<Collection<CharacterDto>> getSortedByName(@RequestParam String sort) {
        if (sort.equals("heroName")) {
            return ResponseEntity.ok(characterService.sortByHeroName());
        }
        if (sort.equals("humanName")) {
            return ResponseEntity.ok(characterService.sortByHumanName());
        }
        return ResponseEntity.ok(characterService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<CharacterDto> add(@RequestBody CharacterDto characterDto) {
        characterService.save(characterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterDto);
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterDto> showCharacter(@PathVariable UUID characterId) {
        try{
            return ResponseEntity.ok(characterService.findById(characterId));
        }
        catch (CharacterNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{characterId}/update")
    public ResponseEntity<CharacterDto> update(@PathVariable UUID characterId, @RequestBody CharacterDto characterDto) {
        characterService.update(characterDto,characterId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(characterDto);
    }

    @DeleteMapping("/{characterId}")
    public void delete(@PathVariable UUID characterId) {
        characterService.delete(characterService.findById(characterId));
    }

    @GetMapping("/{characterId}/comics")
    public ResponseEntity<Collection<ComicBookDto>> showComics (@PathVariable UUID characterId) {
        return ResponseEntity.ok(comicBookService.findComicsByCharacter(characterService.findById(characterId)));
    }

    @PostMapping("/{characterId}/comics/add")
    public ResponseEntity<Collection<ComicBookDto>> addComicBook(
            @PathVariable UUID characterId,
            @RequestBody Collection<ComicBookDto> comicBookDtoCollection
    ) {
        for (ComicBookDto comicBookDto: comicBookDtoCollection)
            characterService.addComicBookForCharacter(characterId, comicBookDto.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(comicBookDtoCollection);
    }

    @DeleteMapping("/{characterId}/comics/{comicBookId}")
    public void deleteComicBook(@PathVariable UUID characterId, @PathVariable UUID comicBookId) {
        characterService.deleteComicBookForCharacter(characterId, comicBookId);
    }

}