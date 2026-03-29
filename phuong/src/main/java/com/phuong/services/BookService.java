package com.phuong.services;

import com.phuong.exception.BookException;
import com.phuong.modal.Book;
import com.phuong.payload.dto.BookDTO;
import com.phuong.payload.request.BookSearchRequest;
import com.phuong.payload.response.PageResponse;

import java.util.List;

public interface BookService {

    BookDTO createBook(BookDTO bookDTO) throws BookException;

    List<BookDTO> createBooksBulk(List<BookDTO> bookDTOs) throws BookException;

    BookDTO getBookById(Long bookId) throws BookException;

    BookDTO getBookByISBN(String isbn) throws BookException;

    BookDTO updateBook(Long bookId, BookDTO bookDTO) throws BookException;

    void deleteBook(Long bookId) throws BookException;

    void hardDeleteBook(Long bookId) throws BookException;

    List<BookDTO> getBooksByAuthor(String author) throws BookException;

    PageResponse<BookDTO> searchBooksWithFilters(
        BookSearchRequest searchRequest);

    Long getTotalActiveBooks();

    Long getTotalAvailableBooks();
}
