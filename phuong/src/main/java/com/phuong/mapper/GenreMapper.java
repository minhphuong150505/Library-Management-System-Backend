package com.phuong.mapper;

import com.phuong.modal.Genre;
import com.phuong.payload.dto.GenreDTO;

import java.util.stream.Collectors;

public class GenreMapper {
    public static GenreDTO toDTO(Genre savedGenre){
        if(savedGenre==null){
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

        dto.setSubGenres(savedGenre.getSubGenres().stream()
                .filter(subGenres-> subGenres.getActive())
                .map(subGenres -> toDTO(subGenres)).collect(Collectors.toList()));
//        dto.setBookCount((long) (savedGenre.getBook()) );
      return dto;
    }
}
