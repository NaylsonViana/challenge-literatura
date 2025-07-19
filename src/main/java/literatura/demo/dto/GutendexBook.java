package literatura.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexBook {
    private int id;
    private String title;
    private List<GutendexAuthor> authors;
    private List<String> languages;
    private int download_count;
    
    // Construtores
    public GutendexBook() {}
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public List<GutendexAuthor> getAuthors() {
        return authors;
    }
    
    public void setAuthors(List<GutendexAuthor> authors) {
        this.authors = authors;
    }
    
    public List<String> getLanguages() {
        return languages;
    }
    
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
    
    public int getDownload_count() {
        return download_count;
    }
    
    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }
} 