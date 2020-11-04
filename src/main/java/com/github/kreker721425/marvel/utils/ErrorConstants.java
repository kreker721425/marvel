package com.github.kreker721425.marvel.utils;

import com.github.kreker721425.marvel.dto.ErrorDto;

public class ErrorConstants {

    public static final ErrorDto ERROR_CHARACTER_NOT_FOUND = new ErrorDto(404, "error.character.not_found");

    public static final ErrorDto ERROR_COMIC_BOOK_NOT_FOUND = new ErrorDto(404, "error.comic_book.not_found");

    public static final ErrorDto ERROR_FILE_NOT_FOUND = new ErrorDto(404, "error.file.not_found");

    public static final ErrorDto ERROR_FILE_CAN_NOT_SAVE = new ErrorDto(400, "error.file.can_not_save");

    public static final ErrorDto ERROR_FILE_IS_EMPTY = new ErrorDto(404, "error.file.is_empty");
}
