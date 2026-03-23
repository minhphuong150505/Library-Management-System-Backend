package com.phuong.services.impl;

import com.phuong.mapper.GenreMapper;
import com.phuong.modal.Genre;
import com.phuong.payload.dto.GenreDTO;
import com.phuong.repository.GenreRepository;
import com.phuong.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    @Autowired
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = genreMapper.toEntity(genreDTO);
        Genre savedGenre = genreRepository.save(genre);

        return genreMapper.toDTO(savedGenre);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().filter(genre -> genre.getActive())
                .map(genreMapper ::toDTO).collect(Collectors.toList());
    }

}