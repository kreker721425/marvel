package com.github.kreker721425.marvel.rest;

import com.github.kreker721425.marvel.dto.ErrorDto;
import com.github.kreker721425.marvel.exception.CharacterNotFoundException;
import com.github.kreker721425.marvel.exception.ComicBookNotFoundException;
import com.github.kreker721425.marvel.exception.FileIsEmptyException;
import com.github.kreker721425.marvel.exception.FileStorageException;
import com.github.kreker721425.marvel.utils.ErrorConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorRestController extends AbstractErrorController {
    private final ErrorAttributes errorAttributes;
    private final String path;

    public ErrorRestController(ErrorAttributes errorAttributes, @Value("${server.error.path:${error.path:/error}}") String path) {
        super(errorAttributes);
        this.errorAttributes = errorAttributes;
        this.path = path;
    }

    @RequestMapping
    public ResponseEntity<ErrorDto> error(HttpServletRequest request) {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Throwable error = errorAttributes.getError(webRequest);
        ErrorDto errorDto = new ErrorDto(getStatus(request).value(), "error.unknown");
        if (error == null) {
            return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
        }
        if (error instanceof CharacterNotFoundException) {
            return getErrorDto(error, new ErrorDto(404, "error.character.not_found"));
        }
        if (error instanceof ComicBookNotFoundException) {
            return getErrorDto(error, new ErrorDto(404, "error.comic_book.not_found"));
        }
        if (error instanceof FileNotFoundException) {
            return getErrorDto(error, new ErrorDto(404, "error.file.not_found"));
        }
        if (error instanceof FileStorageException) {
            return getErrorDto(error, new ErrorDto(400, "error.file.can_not_save"));
        }
        if (error instanceof FileIsEmptyException) {
            return getErrorDto(error, new ErrorDto(404, "error.file.is_empty"));
        }

        return getErrorDto(error, errorDto);
    }

    private ResponseEntity<ErrorDto> getErrorDto(Throwable error, ErrorDto errorDto) {
        error.printStackTrace();
        return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
    }

    @Override
    public String getErrorPath() {
        return path;
    }
}
