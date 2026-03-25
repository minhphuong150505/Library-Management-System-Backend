package com.phuong.HomeController;


 import com.phuong.payload.dto.GenreDTO;
import com.phuong.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    @PostMapping("/create")
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genreDTO) {
        GenreDTO createdGenre = genreService.createGenre(genreDTO);
        return ResponseEntity.ok(createdGenre);
    }
//
//    @GetMapping("/create")
//    public ResponseEntity<GenreDTO> getAllGenres(@RequestBody GenreDTO genreDTO) {
//        List<GenreDTO> genres = genreService.getAllGenres();
//        return ResponseEntity.ok(genres);
//    }
}
