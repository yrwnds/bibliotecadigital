package br.csi.bibliotecadigital.service;

import br.csi.bibliotecadigital.model.emprestimo.Emprestimo;
import br.csi.bibliotecadigital.model.emprestimo.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmprestimoService {
    private final EmprestimoRepository repository;
    public EmprestimoService(EmprestimoRepository repository) {
        this.repository = repository;
    }

    public void salvar(Emprestimo emprestimo) {
        this.repository.save(emprestimo);
    }

    public List<Emprestimo> listar() {
        return this.repository.findAll();
    }

    public Emprestimo buscarPorId(Long id) {
        return this.repository.findById(id).get();
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Emprestimo emprestimo) {
        Emprestimo e = this.repository.getReferenceById(emprestimo.getId());
        e.setDatapego(emprestimo.getDatapego());
        e.setDataprazo(emprestimo.getDataprazo());
        e.setLivro(emprestimo.getLivro());
        e.setUsuario(emprestimo.getUsuario());
        e.setStatus(emprestimo.getStatus());
        this.repository.save(e);
    }

    public void atualizarUUID(Emprestimo emprestimo){
        Emprestimo e = this.repository.findEmprestimoByUuid(emprestimo.getUuid());

        this.repository.save(e);
    }

    public Emprestimo getEmprestimoUuid(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findEmprestimoByUuid(uuidformatado);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteEmprestimoByUuid(UUID.fromString(uuid));
    }  
}
