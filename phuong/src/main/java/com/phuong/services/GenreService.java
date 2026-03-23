package com.phuong.services;

import com.phuong.modal.Genre;
import com.phuong.payload.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    GenreDTO createGenre(GenreDTO genre);

    List<GenreDTO> getAllGenres();
}
