package proacc.umkm.controllers;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import proacc.umkm.entities.Movie;
import proacc.umkm.utils.ApiResponse;
import proacc.umkm.repositories.MovieRepository;

@RestController
@RequestMapping(value = "/api/v1/movies")
public class MovieController {

    @Autowired
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Movie>>> index(@RequestParam(required = false) String search) throws JSONException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println(formattedDateTime);
        List<Movie> movies = movieRepository.searchMovie(search);
        ApiResponse<List<Movie>> response = new ApiResponse<>(true, movies);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<Movie>> store(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        ApiResponse<Movie> response = new ApiResponse<>(true, savedMovie);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<Movie>>> show(@PathVariable Long id) throws JSONException {
        Optional<Movie> movie = movieRepository.findById(id);
        ApiResponse<Optional<Movie>> response = new ApiResponse<>(true, movie);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Movie>> update(@PathVariable Long id, @RequestBody Movie request) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie == null) return ResponseEntity.notFound().build();

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        movie.setName(request.getName());
        movie.setEmail(request.getEmail());
        movie.setUpdatedAt(formattedDateTime);

        Movie updatedMovie = movieRepository.save(movie);
        ApiResponse<Movie> response = new ApiResponse<>(true, updatedMovie);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> destroy(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie == null) {
            ApiResponse<String> notFoundResponse = new ApiResponse<>(false, "Movie not found");
            return ResponseEntity.status(404).body(notFoundResponse);
        }

        movieRepository.delete(movie);
        ApiResponse<String> response = new ApiResponse<>(true, "Movie with ID " + id + " has been deleted.");
        return ResponseEntity.ok(response);
    }
}
