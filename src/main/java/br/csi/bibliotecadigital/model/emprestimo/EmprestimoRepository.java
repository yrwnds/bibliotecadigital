package br.csi.bibliotecadigital.model.emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    public Emprestimo findEmprestimoByUuid(UUID uuid);
    public void deleteEmprestimoByUuid(UUID uuid);

    public List<Emprestimo> findEmprestimoByUsuarioId(long usuarioId);
    public List<Emprestimo> findEmprestimoByLivroIsbn(long Isbn);

    public Emprestimo findEmprestimoByLivroIsbnAndUsuarioId(long Isbn, long usuarioId);

    public List<Emprestimo> findEmprestimoByDatapego(Timestamp datapego);
}
