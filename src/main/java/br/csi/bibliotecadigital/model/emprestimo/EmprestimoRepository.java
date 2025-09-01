package br.csi.bibliotecadigital.model.emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    public Emprestimo findEmprestimoByUuid(UUID uuid);
    public void deleteEmprestimoByUuid(UUID uuid);
}
