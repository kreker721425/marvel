package com.github.kreker721425.marvel.mapper;

import com.github.kreker721425.marvel.dto.ComicBookDto;
import com.github.kreker721425.marvel.entity.ComicBookEntity;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel="spring")
public interface ComicBookMapper {

    ComicBookDto toComicBookDto(ComicBookEntity comicBookEntity);

    Collection<ComicBookDto> toComicsDto(Collection<ComicBookEntity> comicsEntity);

    ComicBookEntity toComicBookEntity(ComicBookDto comicsDto);

    Collection<ComicBookEntity> toComicsEntity(Collection<ComicBookDto> comicsDto);
}
