package com.phuong.services.impl;

import com.phuong.modal.Genre;
import com.phuong.payload.dto.GenreDTO;
import com.phuong.repository.GenreRepository;
import com.phuong.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    @Autowired
    private final GenreRepository genreRepository;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = Genre.builder()
                .code(genreDTO.getCode())
                .name(genreDTO.getName())
                .description(genreDTO.getDescription())
                .displayOrder(genreDTO.getDisplayOrder())
                .active(genreDTO.getActive())
                .build();
        if (genreDTO.getParentGenreId() != null) {
            Genre parentGenre = genreRepository.findById(genreDTO.getParentGenreId()).get();
            genre.setParentGenre(parentGenre);
        }
        Genre savedGenre = genreRepository.save(genre);

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

//        dto.setSubGenres(savedGenre.getSubGenres().stream()
//                .filter(subGenres-> subGenres.getActive())
//                .map(subGenres -> ));
//        dto.setBookCount((long) (savedGenre.getBook()) );
        return dto;
    }
}