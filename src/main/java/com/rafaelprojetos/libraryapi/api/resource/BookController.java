package com.rafaelprojetos.libraryapi.api.resource;

import com.rafaelprojetos.libraryapi.api.dto.BookDTO;
import com.rafaelprojetos.libraryapi.api.exception.ApiErros;
import com.rafaelprojetos.libraryapi.exception.BusinessException;
import com.rafaelprojetos.libraryapi.model.entity.Book;
import com.rafaelprojetos.libraryapi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService service;
    private ModelMapper modelMapper;

    public BookController(BookService service, ModelMapper mapper) {

        this.service = service;
        this.modelMapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody @Valid BookDTO dto) {
        Book entity = modelMapper.map(dto, Book.class);

        entity = service.save(entity);

        return modelMapper.map(entity, BookDTO.class);
    }

    @GetMapping("{id}")
    public BookDTO get (@PathVariable Long id ){
        return service
                .getById(id)
                .map(book -> modelMapper.map(book, BookDTO.class) )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return new ApiErros(bindingResult);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleBusinessException(BusinessException ex) {
        return new ApiErros(ex);
    }
}
