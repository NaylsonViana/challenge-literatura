package literatura.demo.controller;

import literatura.demo.model.Autor;
import literatura.demo.model.Livro;
import literatura.demo.service.LiteraluraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class LiteraluraController implements CommandLineRunner {
    
    @Autowired
    private LiteraluraService literaluraService;
    
    private final Scanner scanner = new Scanner(System.in);
    
    @Override
    public void run(String... args) throws Exception {
        exibirMenu();
    }
    
    private void exibirMenu() {
        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("🎯 LITERALURA - CATÁLOGO DE LIVROS 🎯");
            System.out.println("=".repeat(50));
            System.out.println("1 - Buscar livro pelo título");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores");
            System.out.println("4 - Listar autores vivos em determinado ano");
            System.out.println("5 - Listar livros em determinado idioma");
            System.out.println("6 - Buscar livros por autor");
            System.out.println("7 - Top 10 livros mais baixados");
            System.out.println("8 - Estatísticas do catálogo");
            System.out.println("0 - Sair");
            System.out.println("=".repeat(50));
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine().trim();
            
            try {
                switch (opcao) {
                    case "1":
                        buscarLivroPorTitulo();
                        break;
                    case "2":
                        listarLivrosRegistrados();
                        break;
                    case "3":
                        listarAutores();
                        break;
                    case "4":
                        listarAutoresVivosNoAno();
                        break;
                    case "5":
                        listarLivrosPorIdioma();
                        break;
                    case "6":
                        buscarLivrosPorAutor();
                        break;
                    case "7":
                        listarTopLivrosPorDownloads();
                        break;
                    case "8":
                        exibirEstatisticas();
                        break;
                    case "0":
                        System.out.println("👋 Obrigado por usar o LiterAlura!");
                        return;
                    default:
                        System.out.println("❌ Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.err.println("❌ Erro: " + e.getMessage());
            }
            
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
        }
    }
    
    private void buscarLivroPorTitulo() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine().trim();
        
        if (titulo.isEmpty()) {
            System.out.println("❌ Título não pode estar vazio!");
            return;
        }
        
        try {
            Livro livro = literaluraService.buscarEAdicionarLivro(titulo);
            System.out.println("\n✅ Livro encontrado e adicionado ao catálogo:");
            exibirLivroCompleto(livro);
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
    
    private void listarLivrosRegistrados() {
        List<Livro> livros = literaluraService.listarTodosLivros();
        
        if (livros.isEmpty()) {
            System.out.println("📚 Nenhum livro registrado no catálogo.");
            return;
        }
        
        System.out.println("\n📚 LIVROS REGISTRADOS:");
        System.out.println("-".repeat(80));
        livros.forEach(this::exibirLivro);
    }
    
    private void listarAutores() {
        List<Autor> autores = literaluraService.listarTodosAutores();
        
        if (autores.isEmpty()) {
            System.out.println("👤 Nenhum autor registrado no catálogo.");
            return;
        }
        
        System.out.println("\n👤 AUTORES REGISTRADOS:");
        System.out.println("-".repeat(80));
        autores.forEach(this::exibirAutor);
    }
    
    private void listarAutoresVivosNoAno() {
        System.out.print("Digite o ano: ");
        String anoStr = scanner.nextLine().trim();
        
        try {
            Integer ano = Integer.parseInt(anoStr);
            List<Autor> autores = literaluraService.listarAutoresVivosNoAno(ano);
            
            if (autores.isEmpty()) {
                System.out.println("👤 Nenhum autor estava vivo em " + ano + ".");
                return;
            }
            
            System.out.println("\n👤 AUTORES VIVOS EM " + ano + ":");
            System.out.println("-".repeat(80));
            autores.forEach(this::exibirAutor);
        } catch (NumberFormatException e) {
            System.out.println("❌ Ano inválido! Digite um número.");
        }
    }
    
    private void listarLivrosPorIdioma() {
        System.out.println("Idiomas disponíveis:");
        System.out.println("PT - Português");
        System.out.println("EN - Inglês");
        System.out.println("ES - Espanhol");
        System.out.println("FR - Francês");
        System.out.print("Digite o código do idioma: ");
        String idioma = scanner.nextLine().trim().toUpperCase();
        
        List<Livro> livros = literaluraService.listarLivrosPorIdioma(idioma);
        
        if (livros.isEmpty()) {
            System.out.println("📚 Nenhum livro encontrado no idioma " + idioma + ".");
            return;
        }
        
        System.out.println("\n📚 LIVROS EM " + idioma + ":");
        System.out.println("-".repeat(80));
        livros.forEach(this::exibirLivro);
    }
    
    private void buscarLivrosPorAutor() {
        System.out.print("Digite o nome do autor: ");
        String nomeAutor = scanner.nextLine().trim();
        
        if (nomeAutor.isEmpty()) {
            System.out.println("❌ Nome do autor não pode estar vazio!");
            return;
        }
        
        List<Livro> livros = literaluraService.buscarLivrosPorAutor(nomeAutor);
        
        if (livros.isEmpty()) {
            System.out.println("📚 Nenhum livro encontrado para o autor: " + nomeAutor);
            return;
        }
        
        System.out.println("\n📚 LIVROS DE " + nomeAutor.toUpperCase() + ":");
        System.out.println("-".repeat(80));
        livros.forEach(this::exibirLivro);
    }
    
    private void listarTopLivrosPorDownloads() {
        List<Livro> livros = literaluraService.listarTopLivrosPorDownloads();
        
        if (livros.isEmpty()) {
            System.out.println("📚 Nenhum livro registrado no catálogo.");
            return;
        }
        
        System.out.println("\n🏆 TOP 10 LIVROS MAIS BAIXADOS:");
        System.out.println("-".repeat(80));
        
        int limit = Math.min(livros.size(), 10);
        for (int i = 0; i < limit; i++) {
            Livro livro = livros.get(i);
            System.out.printf("%d. %s - %s (%s) - %d downloads%n", 
                    i + 1, 
                    livro.getTitulo(), 
                    livro.getAutor().getNome(), 
                    livro.getIdioma().toUpperCase(), 
                    livro.getNumeroDownloads());
        }
    }
    
    private void exibirEstatisticas() {
        List<Livro> livros = literaluraService.listarTodosLivros();
        List<Autor> autores = literaluraService.listarTodosAutores();
        
        System.out.println("\n📊 ESTATÍSTICAS DO CATÁLOGO:");
        System.out.println("-".repeat(50));
        System.out.println("📚 Total de livros: " + livros.size());
        System.out.println("👤 Total de autores: " + autores.size());
        
        if (!livros.isEmpty()) {
            long totalDownloads = livros.stream()
                    .mapToLong(Livro::getNumeroDownloads)
                    .sum();
            double mediaDownloads = (double) totalDownloads / livros.size();
            
            System.out.println("⬇️ Total de downloads: " + totalDownloads);
            System.out.printf("📈 Média de downloads por livro: %.2f%n", mediaDownloads);
            
            // Idioma mais comum
            String idiomaMaisComum = livros.stream()
                    .collect(java.util.stream.Collectors.groupingBy(
                            Livro::getIdioma,
                            java.util.stream.Collectors.counting()))
                    .entrySet().stream()
                    .max(java.util.Map.Entry.comparingByValue())
                    .map(java.util.Map.Entry::getKey)
                    .orElse("N/A");
            
            System.out.println("🌍 Idioma mais comum: " + idiomaMaisComum.toUpperCase());
        }
    }
    
    private void exibirLivro(Livro livro) {
        System.out.printf("📖 %s - %s (%s) - %d downloads%n", 
                livro.getTitulo(), 
                livro.getAutor().getNome(), 
                livro.getIdioma().toUpperCase(), 
                livro.getNumeroDownloads());
    }
    
    private void exibirLivroCompleto(Livro livro) {
        System.out.printf("📖 %s%n", livro.getTitulo());
        System.out.printf("   👤 Autor: %s", livro.getAutor().getNome());
        
        // Mostrar anos do autor
        if (livro.getAutor().getAnoNascimento() != null) {
            System.out.printf(" (📅 %d", livro.getAutor().getAnoNascimento());
            if (livro.getAutor().getAnoFalecimento() != null) {
                System.out.printf(" - %d", livro.getAutor().getAnoFalecimento());
            } else {
                System.out.print(" - presente");
            }
            System.out.print(")");
        }
        System.out.println();
        
        System.out.printf("   🌍 Idioma: %s%n", livro.getIdioma().toUpperCase());
        System.out.printf("   ⬇️ Downloads: %d%n", livro.getNumeroDownloads());
    }
    
    private void exibirAutor(Autor autor) {
        // Formatar anos de vida
        String anosVida = "";
        if (autor.getAnoNascimento() != null) {
            anosVida += "📅 " + autor.getAnoNascimento();
            if (autor.getAnoFalecimento() != null) {
                anosVida += " - " + autor.getAnoFalecimento();
                // Calcular idade
                int idade = autor.getAnoFalecimento() - autor.getAnoNascimento();
                anosVida += " (viveu " + idade + " anos)";
            } else {
                anosVida += " - presente";
            }
        }
        
        // Exibir autor com anos
        if (!anosVida.isEmpty()) {
            System.out.printf("👤 %s%n", autor.getNome());
            System.out.printf("   %s%n", anosVida);
        } else {
            System.out.printf("👤 %s (anos não informados)%n", autor.getNome());
        }
        
        // Exibir livros do autor
        if (autor.getLivros() != null && !autor.getLivros().isEmpty()) {
            System.out.println("   📚 Livros:");
            autor.getLivros().forEach(livro -> 
                System.out.printf("      📖 %s (%s) - %d downloads%n", 
                    livro.getTitulo(), 
                    livro.getIdioma().toUpperCase(), 
                    livro.getNumeroDownloads()));
        }
        System.out.println(); // Linha em branco para separar autores
    }
} 