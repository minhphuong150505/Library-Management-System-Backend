package com.phuong.payload.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDTO {
    private Long id;

    @NotBlank(message = "Genre Code is Mandatory")
    private String code;

    @NotBlank(message = "genre Name is Mandatory")
    private String name;

    @Size(max = 500, message = "description must not exceed 500 characters")
    private String description;

    @Min(value = 0, message = "display over cannot be nagative")
    private Integer displayOrder;

    private Boolean active = true;

    private Long parentGenreId;

    private String parentGenreName;

    private List<GenreDTO> subGenres;

    private Long bookCount;

    private String createdAt;
    private String updatedAt;

}
