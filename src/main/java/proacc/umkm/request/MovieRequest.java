package proacc.umkm.request;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;

public class MovieRequest {
    private String name;
    private String email;
    private Long categoryId;

    // Contoh setter getter tanpa lombok
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}