package com.phuong.services.impl;

import com.phuong.exception.GenreException;
import com.phuong.mapper.GenreMapper;
import com.phuong.modal.Genre;
import com.phuong.payload.dto.GenreDTO;
import com.phuong.repository.GenreRepository;
import com.phuong.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public GenreDTO getGenreById(Long genreId) throws GenreException {
        Genre genre = genreRepository.findById(genreId).orElseThrow(
                () -> new GenreException("genre not found")
        );
        return genreMapper.toDTO(genre);
    }

    @Override
    public GenreDTO updateGenre(Long genreId, GenreDTO genreDTO) throws GenreException {
        Genre existingGenre = genreRepository.findById(genreId).orElseThrow(
                () -> new GenreException("Genre not found with id: " + genreId)
        );

        genreMapper.updateEntityFromDTO(genreDTO, existingGenre);

       Genre  updatedGenre = genreRepository.save(existingGenre);

        return genreMapper.toDTO(updatedGenre);
    }

    @Override
    public void deleteGenre(Long genreId) throws GenreException {
        Genre existingGenre = genreRepository.findById(genreId).orElseThrow(
                () -> new GenreException("Genre not found with id: " + genreId)
        );
        existingGenre.setActive(false);
        genreRepository.save(existingGenre);
    }

    @Override
    public void hardDeleteGenre(Long genreId) throws GenreException {
        Genre existingGenre = genreRepository.findById(genreId).orElseThrow(
                () -> new GenreException("Genre not found with id: " + genreId)
        );
        genreRepository.delete(existingGenre);
    }

    @Override
    public List<GenreDTO> getAllActiveGenresWithSubGenres() {
        List<Genre> topLevelGenres = genreRepository
                .findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();


        return genreMapper.;
    }

    @Override
    public List<GenreDTO> getTopLevelGenres() {
        return List.of();
    }

    @Override
    public Page<GenreDTO> searchGenres(String searchTerm, Pageable pageable) {
        return null;
    }

    @Override
    public long getTotalActiveGenres() {
        return 0;
    }

    @Override
    public long getBookCountByGenre(Long genreId) {
        return 0;
    }

}