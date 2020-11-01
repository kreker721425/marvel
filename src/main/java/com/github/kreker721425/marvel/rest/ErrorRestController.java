package com.github.kreker721425.marvel.rest;

import com.github.kreker721425.marvel.dto.ErrorDto;
import com.github.kreker721425.marvel.exception.CharacterNotFoundException;
import com.github.kreker721425.marvel.exception.ComicBookNotFoundException;
import com.github.kreker721425.marvel.exception.FileIsEmptyException;
import com.github.kreker721425.marvel.exception.FileStorageException;
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
        int status = getStatus(request).value();
        String message = "error.unknown";
        if (error == null) {
            return ResponseEntity.status(status).body(
                    new ErrorDto(status, message)
            );
        }
        if (error instanceof CharacterNotFoundException) {
            status = 404;
            message = "error.character.not_found";
            return getErrorDto(error, status, message);
        }
        if (error instanceof ComicBookNotFoundException) {
            status = 404;
            message = "error.comics.not_found";
            return getErrorDto(error, status, message);
        }
        if (error instanceof FileNotFoundException) {
            status = 404;
            message = "error.file.not_found";
            return getErrorDto(error, status, message);
        }
        if (error instanceof FileStorageException) {
            status = 400;
            message = "error.file.can_not_save";
            return getErrorDto(error, status, message);
        }
        if (error instanceof FileIsEmptyException) {
            status = 404;
            message = "error.file.is_empty";
            return getErrorDto(error, status, message);
        }

        return getErrorDto(error, status, message);
    }

    private ResponseEntity<ErrorDto> getErrorDto(Throwable error, int status, String message) {
        error.printStackTrace();
        return ResponseEntity.status(status).body(
                new ErrorDto(status, message)
        );
    }

    @Override
    public String getErrorPath() {
        return path;
    }
}
