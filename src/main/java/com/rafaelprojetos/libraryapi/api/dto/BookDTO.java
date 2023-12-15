package com.rafaelprojetos.libraryapi.api.dto;

import lombok.*;

import jakarta.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotEmpty
    private String isbn;
}

