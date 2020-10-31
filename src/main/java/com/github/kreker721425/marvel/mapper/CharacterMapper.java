package com.github.kreker721425.marvel.mapper;

import com.github.kreker721425.marvel.dto.CharacterDto;
import com.github.kreker721425.marvel.entity.CharacterEntity;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel="spring")
public interface CharacterMapper {

    CharacterDto toCharacterDto(CharacterEntity characterEntity);

    Collection<CharacterDto> toCharacterDto(Collection<CharacterEntity> characterEntities);

    CharacterEntity toCharacterEntity(CharacterDto characterDto);

    Collection<CharacterEntity> toCharacterEntity(Collection<CharacterDto> characterDtoCollection);
}
