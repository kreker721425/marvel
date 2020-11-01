package com.github.kreker721425.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComicBookDto {
    private UUID id;
    private String name;
    private String writer;
    private String description;
    private String image;
}
