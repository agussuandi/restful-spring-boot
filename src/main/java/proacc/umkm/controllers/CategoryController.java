package proacc.umkm.controllers;

import org.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import proacc.umkm.entities.Category;
import proacc.umkm.utils.ApiResponse;
import proacc.umkm.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryController {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<Category>>> index(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        ApiResponse<Page<Category>> response = new ApiResponse<>(true, categories);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> store(@RequestBody Category category) {
        Category createdCategory = categoryRepository.save(category);
        ApiResponse<Category> response = new ApiResponse<>(true, createdCategory);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<Category>>> show(@PathVariable Long id) throws JSONException {
        Optional<Category> category = categoryRepository.findById(id);
        ApiResponse<Optional<Category>> response = new ApiResponse<>(true, category);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> update(@PathVariable Long id, @RequestBody Category request) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) return ResponseEntity.notFound().build();

        category.setName(request.getName());

        Category updatedMovie = categoryRepository.save(category);
        ApiResponse<Category> response = new ApiResponse<>(true, updatedMovie);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> destroy(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            ApiResponse<String> notFoundResponse = new ApiResponse<>(false, "Category not found");
            return ResponseEntity.status(404).body(notFoundResponse);
        }

        categoryRepository.delete(category);
        ApiResponse<String> response = new ApiResponse<>(true, "Category with ID " + id + " has been deleted.");
        return ResponseEntity.ok(response);
    }
}
