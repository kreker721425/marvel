package com.github.kreker721425.marvel.mapper;

import com.github.kreker721425.marvel.dto.ComicBookDto;
import com.github.kreker721425.marvel.entity.ComicBookEntity;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-02T14:33:22+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
@Component
public class ComicBookMapperImpl implements ComicBookMapper {

    @Override
    public ComicBookDto toComicBookDto(ComicBookEntity comicBookEntity) {
        if ( comicBookEntity == null ) {
            return null;
        }

        ComicBookDto comicBookDto = new ComicBookDto();

        comicBookDto.setId( comicBookEntity.getId() );
        comicBookDto.setName( comicBookEntity.getName() );
        comicBookDto.setWriter( comicBookEntity.getWriter() );
        comicBookDto.setDescription( comicBookEntity.getDescription() );
        comicBookDto.setImage( comicBookEntity.getImage() );

        return comicBookDto;
    }

    @Override
    public Collection<ComicBookDto> toComicsDto(Collection<ComicBookEntity> comicsEntity) {
        if ( comicsEntity == null ) {
            return null;
        }

        Collection<ComicBookDto> collection = new ArrayList<ComicBookDto>( comicsEntity.size() );
        for ( ComicBookEntity comicBookEntity : comicsEntity ) {
            collection.add( toComicBookDto( comicBookEntity ) );
        }

        return collection;
    }

    @Override
    public ComicBookEntity toComicBookEntity(ComicBookDto comicsDto) {
        if ( comicsDto == null ) {
            return null;
        }

        ComicBookEntity comicBookEntity = new ComicBookEntity();

        comicBookEntity.setId( comicsDto.getId() );
        comicBookEntity.setName( comicsDto.getName() );
        comicBookEntity.setWriter( comicsDto.getWriter() );
        comicBookEntity.setDescription( comicsDto.getDescription() );
        comicBookEntity.setImage( comicsDto.getImage() );

        return comicBookEntity;
    }

    @Override
    public Collection<ComicBookEntity> toComicsEntity(Collection<ComicBookDto> comicsDto) {
        if ( comicsDto == null ) {
            return null;
        }

        Collection<ComicBookEntity> collection = new ArrayList<ComicBookEntity>( comicsDto.size() );
        for ( ComicBookDto comicBookDto : comicsDto ) {
            collection.add( toComicBookEntity( comicBookDto ) );
        }

        return collection;
    }
}
