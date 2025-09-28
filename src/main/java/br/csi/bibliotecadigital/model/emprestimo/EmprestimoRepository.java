package br.csi.bibliotecadigital.model.emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    public Emprestimo findEmprestimoByUuid(UUID uuid);
    public void deleteEmprestimoByUuid(UUID uuid);

    public List<Emprestimo> findEmprestimoByUsuarioId(long usuarioId);
    public List<Emprestimo> findEmprestimoByLivroIsbn(long Isbn);

    @Query("SELECT e FROM Emprestimo e WHERE e.livro.isbn = :Isbn AND e.usuario.id = :usuarioId AND e.status = 'ATIVO'")
    public Emprestimo findEmprestimoByLivroIsbnAndUsuarioId(@Param("Isbn")long Isbn, @Param("usuarioId")long usuarioId);

    public List<Emprestimo> findEmprestimoByDatapego(Timestamp datapego);
}
