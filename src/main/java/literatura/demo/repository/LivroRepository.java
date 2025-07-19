package literatura.demo.repository;

import literatura.demo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    Optional<Livro> findByTitulo(String titulo);
    
    List<Livro> findByIdioma(String idioma);
    
    @Query("SELECT l FROM Livro l ORDER BY l.numeroDownloads DESC")
    List<Livro> findTopLivrosPorDownloads();
    
    @Query("SELECT l FROM Livro l WHERE l.autor.nome LIKE %:nomeAutor%")
    List<Livro> findByNomeAutor(@Param("nomeAutor") String nomeAutor);
} 