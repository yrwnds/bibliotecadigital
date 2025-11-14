package br.csi.bibliotecadigital.model.livro;

import br.csi.bibliotecadigital.model.categoria.Categoria;
import br.csi.bibliotecadigital.model.usuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    public Livro findLivroByUuid(UUID uuid);
    public void deleteLivroByUuid(UUID uuid);

    public List<Livro> findLivroByTituloContainingIgnoreCase(String titulo);

    public List<Livro> findLivroByAutorContainingIgnoreCase(String autor);

    public List<Livro> findLivroByCategoriaNomeContainingIgnoreCase(String nome);

    public List<Livro> findLivroByLinguagemContainingIgnoreCase(String linguagem);

    @Query("SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(l.autor) LIKE LOWER(CONCAT('%', :keyword, '%')) OR CAST(l.anopublicado AS string) LIKE (CONCAT('%', :keyword, '%'))")
    List<Livro> findLivroByAnyParam(@Param("keyword") String keyword);

    @Query("SELECT l FROM Livro l where l.n_disponivel > 0")
    public List<Livro> findLivroByN_disponivelGreaterThan0();
}
