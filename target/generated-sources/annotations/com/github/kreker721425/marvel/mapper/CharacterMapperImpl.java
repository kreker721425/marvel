package com.github.kreker721425.marvel.mapper;

import com.github.kreker721425.marvel.dto.CharacterDto;
import com.github.kreker721425.marvel.entity.CharacterEntity;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-01T00:46:29+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
@Component
public class CharacterMapperImpl implements CharacterMapper {

    @Override
    public CharacterDto toCharacterDto(CharacterEntity characterEntity) {
        if ( characterEntity == null ) {
            return null;
        }

        CharacterDto characterDto = new CharacterDto();

        characterDto.setId( characterEntity.getId() );
        characterDto.setHeroName( characterEntity.getHeroName() );
        characterDto.setHumanName( characterEntity.getHumanName() );
        characterDto.setDescription( characterEntity.getDescription() );
        characterDto.setImage( characterEntity.getImage() );

        return characterDto;
    }

    @Override
    public Collection<CharacterDto> toCharacterDto(Collection<CharacterEntity> characterEntities) {
        if ( characterEntities == null ) {
            return null;
        }

        Collection<CharacterDto> collection = new ArrayList<CharacterDto>( characterEntities.size() );
        for ( CharacterEntity characterEntity : characterEntities ) {
            collection.add( toCharacterDto( characterEntity ) );
        }

        return collection;
    }

    @Override
    public CharacterEntity toCharacterEntity(CharacterDto characterDto) {
        if ( characterDto == null ) {
            return null;
        }

        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setId( characterDto.getId() );
        characterEntity.setHeroName( characterDto.getHeroName() );
        characterEntity.setHumanName( characterDto.getHumanName() );
        characterEntity.setDescription( characterDto.getDescription() );
        characterEntity.setImage( characterDto.getImage() );

        return characterEntity;
    }

    @Override
    public Collection<CharacterEntity> toCharacterEntity(Collection<CharacterDto> characterDtoCollection) {
        if ( characterDtoCollection == null ) {
            return null;
        }

        Collection<CharacterEntity> collection = new ArrayList<CharacterEntity>( characterDtoCollection.size() );
        for ( CharacterDto characterDto : characterDtoCollection ) {
            collection.add( toCharacterEntity( characterDto ) );
        }

        return collection;
    }
}
