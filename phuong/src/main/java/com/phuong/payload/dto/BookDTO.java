package com.phuong.payload.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private Long  id;

    @NotBlank(message = "ISBN is mandatory")
    private String isbn;

    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Size(min = 1, max = 255, message = "author must be between 1 and 255 characters")
    private String author;

    @NotNull(message = "Genre is mandatory")
    private  Long genreId;

    private String genreName;

    private String genreCode;

    @Size(max = 100, message = "Publisher name must not exceed 100 characters")
    private String publisher;

    private LocalDate publishedDate;

    @Size(max = 20, message = "Languae name must not exceed 20 characters")
    private String languae;

    @Min(value = 1, message = "Pages must be at least 1")
    @Max(value = 50000, message = "Pages must not exceed 50000")
    private Integer pages;

    @Size(max = 2000, message = "Description must not exceed 2000 character")
    private String description;

    @Min(value = 0, message = "Total copies cannot be negative")
    @NotNull(message = "Total is mandatory")
    private Integer totalCopies;

    @Min(value = 0, message = "Available copies cannot be negative")
    @NotNull(message = "Available copies is mandatory")
    private Integer availableCopies;

    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    @Digits(integer = 8, fraction = 2, message = "Price must be have at must 8 digits and 2 fraction")
    private BigDecimal price;

    @Size(max = 500, message = "Image URL must not exceep 500")
    private String coverImageUrl;

    private Boolean alreadyLoan;
    private Boolean alreadyHaveReservation;

    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
