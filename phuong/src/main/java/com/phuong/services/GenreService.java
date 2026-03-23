package com.phuong.services;

import com.phuong.modal.Genre;
import com.phuong.payload.dto.GenreDTO;

public interface GenreService {

    GenreDTO createGenre(GenreDTO genre);

}
