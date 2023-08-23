package proacc.umkm.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proacc.umkm.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // You can add custom query methods here if needed
    Page<Category> findAll(Pageable pageable);

    @Query(value = "select * from categories where :search is null or name like %:search%", nativeQuery = true)
    List<Category> searchCategory(@Param("search") String search);
}
