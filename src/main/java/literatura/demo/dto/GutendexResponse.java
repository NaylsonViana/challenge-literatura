package literatura.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {
    private int count;
    private String next;
    private String previous;
    private List<GutendexBook> results;
    
    // Construtores
    public GutendexResponse() {}
    
    // Getters e Setters
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public String getNext() {
        return next;
    }
    
    public void setNext(String next) {
        this.next = next;
    }
    
    public String getPrevious() {
        return previous;
    }
    
    public void setPrevious(String previous) {
        this.previous = previous;
    }
    
    public List<GutendexBook> getResults() {
        return results;
    }
    
    public void setResults(List<GutendexBook> results) {
        this.results = results;
    }
} 