package com.phuong.reponsitory;

import com.phuong.modal.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreReponsitory extends JpaRepository<Genre, Long> {

}
