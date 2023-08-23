package proacc.umkm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proacc.umkm.entities.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // You can add custom query methods here if needed

    @Query(value = "select * from categories where :search is null or name like %:search%", nativeQuery = true)
    List<Category> searchCategory(@Param("search") String search);
}
