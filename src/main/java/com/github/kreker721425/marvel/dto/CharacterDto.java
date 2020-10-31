package com.github.kreker721425.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDto  {
    private UUID id;
    private String heroName;
    private String humanName;
    private String description;
    private String image;
}
