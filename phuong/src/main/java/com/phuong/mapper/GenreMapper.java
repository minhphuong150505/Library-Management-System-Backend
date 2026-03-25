package com.phuong.mapper;

import com.phuong.modal.Genre;
import com.phuong.payload.dto.GenreDTO;
import com.phuong.repository.GenreRepository;
import com.phuong.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenreMapper {

    private final GenreRepository genreRepository;

    public GenreDTO toDTO(Genre savedGenre) {
        if (savedGenre == null) {
            return null;
        }
        GenreDTO dto = GenreDTO.builder()
                .id(savedGenre.getId())
                .code(savedGenre.getCode())
                .name(savedGenre.getName())
                .description(savedGenre.getDescription())
                .displayOrder(savedGenre.getDisplayOrder())
                .active(savedGenre.getActive())
                .createdAt(savedGenre.getCreatedAt().toString())
                .updatedAt(savedGenre.getUpdatedAt().toString())
                .build();

        if (savedGenre.getParentGenre() != null) {
            dto.setParentGenreId(savedGenre.getParentGenre().getId());
            dto.setParentGenreName(savedGenre.getParentGenre().getName());
        }

        if (savedGenre.getSubGenres() != null && !savedGenre.getSubGenres().isEmpty()) {
            dto.setSubGenres(savedGenre.getSubGenres().stream()
                    .filter(subGenres -> subGenres.getActive())
                    .map(subGenres -> toDTO(subGenres)).collect(Collectors.toList()));
        }
//        dto.setBookCount((long) (savedGenre.getBook()) );
        return dto;
    }

    public Genre toEntity(GenreDTO genreDTO) {
        if (genreDTO == null) {
            return null;
        }

        Genre genre = Genre.builder()
                .id(genreDTO.getId())
                .code(genreDTO.getCode())
                .name(genreDTO.getName())
                .description(genreDTO.getDescription())
                .displayOrder(genreDTO.getDisplayOrder())
                .active(genreDTO.getActive())
                .build();
        ;

        if (genreDTO.getParentGenreId() != null) {
            genreRepository.findById(genreDTO.getParentGenreId())
                    .ifPresent(genre::setParentGenre);

        }

        return genre;
    }

    public void updateEntityFromDTO(GenreDTO dto, Genre existingGenre) {
        if (dto == null || existingGenre == null) {
            return;
        }

        existingGenre.setCode(dto.getCode());
        existingGenre.setName(dto.getName());
        existingGenre.setDescription(dto.getDescription());
        existingGenre.setDisplayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0);
        if (dto.getActive() != null) {
            existingGenre.setActive(dto.getActive());
        }
        if (dto.getParentGenreId() != null) {
            genreRepository.findById(dto.getParentGenreId())
                    .ifPresent(existingGenre::setParentGenre);
        }
    }

    public List<GenreDTO> toDTOList(List<Genre> genreList) {
        return genreList.stream()
    }
}
