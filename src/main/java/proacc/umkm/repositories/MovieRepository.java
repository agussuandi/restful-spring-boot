package proacc.umkm.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import proacc.umkm.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "select * from movies where :search is null or name like %:search%", nativeQuery = true)
    List<Movie> searchMovie(@Param("search") String search);
}