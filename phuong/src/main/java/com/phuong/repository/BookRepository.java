package com.phuong.repository;

import com.phuong.modal.Book;
import com.phuong.payload.dto.BookDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String Isbn);

    boolean existsByIsbn(String Isbn);

    // book - java propraming
    // java
    @Query("select b from Book b where" +
            ":searchTerm is null or "+
            "lower(b.title) like lower(concat ('%', :searchTerm,'%')) OR " +
            "lower(b.author) like lower(concat ('%', :searchTerm,'%')) OR " +
            "lower(b.isbn) like lower(concat ('%', :searchTerm,'%')) OR " +
            "(:genreId is null or b.genre.id = :genreId) AND " +
            ":availableOnly == false Or b.availableCopies 0) AND" +
            "where b.active = true")
    Page<Book> searchBookWithFilters(
            @Param("searchTerm") String searchTerm,
            @Param("genreId") Long genreId,
            @Param("availableOnly") boolean availableOnly,
            Pageable pageable
    );

    long countByActiveTrue();

    @Query("select count(b) from Book b Where b.availableCopies > 0 and b.active = true" )
    long countAvailableBooks();

    @Query("select b from Book b " +
            "where b.author = :author")
    Optional<Book> getAllByAuthor(@Param("author") String author);
}
