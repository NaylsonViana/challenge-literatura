package literatura.demo.service;

import literatura.demo.dto.GutendexBook;
import literatura.demo.dto.GutendexAuthor;
import literatura.demo.model.Autor;
import literatura.demo.model.Livro;
import literatura.demo.repository.AutorRepository;
import literatura.demo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiteraluraService {
    
    @Autowired
    private GutendexService gutendexService;
    
    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private LivroRepository livroRepository;
    
    public Livro buscarEAdicionarLivro(String titulo) {
        // Buscar na API Gutendex
        List<GutendexBook> livrosEncontrados = gutendexService.buscarLivrosPorTitulo(titulo);
        
        if (livrosEncontrados.isEmpty()) {
            throw new RuntimeException("Nenhum livro encontrado com o título: " + titulo);
        }
        
        // Pegar o primeiro resultado
        GutendexBook livroApi = livrosEncontrados.get(0);
        
        // Verificar se o livro já existe no banco
        Optional<Livro> livroExistente = livroRepository.findByTitulo(livroApi.getTitle());
        if (livroExistente.isPresent()) {
            return livroExistente.get();
        }
        
        // Processar autores
        if (livroApi.getAuthors() == null || livroApi.getAuthors().isEmpty()) {
            throw new RuntimeException("Livro não possui informações de autor");
        }
        
        GutendexAuthor autorApi = livroApi.getAuthors().get(0);
        
        // Buscar ou criar autor
        Autor autor = autorRepository.findByNome(autorApi.getName())
                .orElseGet(() -> {
                    Autor novoAutor = new Autor(
                            autorApi.getName(),
                            autorApi.getBirth_year(),
                            autorApi.getDeath_year()
                    );
                    return autorRepository.save(novoAutor);
                });
        
        // Determinar idioma principal
        String idioma = "en"; // padrão
        if (livroApi.getLanguages() != null && !livroApi.getLanguages().isEmpty()) {
            idioma = livroApi.getLanguages().get(0);
        }
        
        // Criar e salvar livro
        Livro livro = new Livro(
                livroApi.getTitle(),
                idioma,
                livroApi.getDownload_count(),
                autor
        );
        
        return livroRepository.save(livro);
    }
    
    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }
    
    public List<Autor> listarTodosAutores() {
        return autorRepository.findAll();
    }
    
    public List<Autor> listarAutoresVivosNoAno(Integer ano) {
        return autorRepository.findAutoresVivosNoAno(ano);
    }
    
    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }
    
    // Métodos extras
    public List<Livro> buscarLivrosPorAutor(String nomeAutor) {
        return livroRepository.findByNomeAutor(nomeAutor);
    }
    
    public List<Livro> listarTopLivrosPorDownloads() {
        return livroRepository.findTopLivrosPorDownloads();
    }
    
    public List<Autor> listarAutoresPorIdioma(String idioma) {
        return autorRepository.findAutoresPorIdioma(idioma);
    }
} 