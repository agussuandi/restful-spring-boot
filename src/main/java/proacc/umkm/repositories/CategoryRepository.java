package proacc.umkm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proacc.umkm.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // You can add custom query methods here if needed
}
