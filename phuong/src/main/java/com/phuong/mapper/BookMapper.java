package com.phuong.mapper;

import com.phuong.exception.BookException;
import com.phuong.modal.Book;
import com.phuong.modal.Genre;
import com.phuong.payload.dto.BookDTO;
import com.phuong.payload.dto.GenreDTO;
import com.phuong.repository.BookRepository;
import com.phuong.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {

    final GenreRepository genreRepository;

    public BookDTO toDTO(Book book) {
        if(book == null) return null;

        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .genreId(book.getGenre().getId())
                .genreCode(book.getGenre().getCode())
                .genreName(book.getGenre().getName())
                .publisher(book.getPublisher())
                .publishedDate(book.getPublishedDate())
                .languae(book.getLanguage())
                .pages(book.getPages())
                .description(book.getDescription())
                .totalCopies(book.getTotalCopies())
                .availableCopies(book.getAvailableCopies())
                .price(book.getPrice())
                .coverImageUrl(book.getCoverImageUrl())
                .active(book.getActive())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();

    }

    public Book toEntity(BookDTO bookDTO) throws BookException {
        if (bookDTO == null) {
            return null;
        }

        Genre genre = null;
        if (bookDTO.getGenreId() != null) {
            genre = genreRepository.findById(bookDTO.getGenreId())
                    .orElseThrow(() -> new BookException(
                            "Book with ID " + bookDTO.getGenreId() + "not found"));
        }

        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .isbn(bookDTO.getIsbn())
                .genre(genre)
                .publisher(bookDTO.getPublisher())
                .publishedDate(bookDTO.getPublishedDate())
                .language(bookDTO.getLanguae())
                .pages(bookDTO.getPages())
                .description(bookDTO.getDescription())
                .totalCopies(bookDTO.getTotalCopies())
                .availableCopies(bookDTO.getAvailableCopies())
                .price(bookDTO.getPrice())
                .coverImageUrl(bookDTO.getCoverImageUrl())
                .active(true)
                .build();
    }

    public void updateEntityFromDTO(BookDTO dto, Book book) throws BookException {
        if (dto == null || book == null) {
            return;
        }

        // ISBN should not be updated
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());

        if (dto.getGenreId() != null) {
            Genre genre = genreRepository.findById(dto.getGenreId())
                    .orElseThrow(() -> new BookException("Genre with ID " + dto.getGenreId() + " not found"));
            book.setGenre(genre);
        }

        book.setPublisher(dto.getPublisher());
        book.setPublishedDate(dto.getPublishedDate());
        book.setLanguage(dto.getLanguae());
        book.setPages(dto.getPages());
        book.setDescription(dto.getDescription());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getAvailableCopies());
        book.setPrice(dto.getPrice());
        book.setCoverImageUrl(dto.getCoverImageUrl());

        if (dto.getActive() != null) {
            book.setActive(dto.getActive());
        }
    }
}
