package literatura.demo.service;

import literatura.demo.dto.GutendexResponse;
import literatura.demo.dto.GutendexBook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class GutendexService {
    
    @Value("${api.gutendex.base-url}")
    private String baseUrl;
    
    private final RestTemplate restTemplate;
    
    public GutendexService() {
        this.restTemplate = new RestTemplate();
    }
    
    public List<GutendexBook> buscarLivrosPorTitulo(String titulo) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .queryParam("search", titulo)
                .build()
                .toUriString();
        
        try {
            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
            return response != null ? response.getResults() : List.of();
        } catch (Exception e) {
            System.err.println("Erro ao buscar livros na API Gutendex: " + e.getMessage());
            return List.of();
        }
    }
} 