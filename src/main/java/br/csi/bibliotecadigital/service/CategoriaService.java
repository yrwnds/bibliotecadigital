package br.csi.bibliotecadigital.service;

import br.csi.bibliotecadigital.model.categoria.Categoria;
import br.csi.bibliotecadigital.model.categoria.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;
    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Categoria administrador) {
        this.repository.save(administrador);
    }

    public List<Categoria> listar() {
        return this.repository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return this.repository.findById(id).get();
    }

    public Categoria getCategoriaUuid(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findCategoriaByUuid(uuidformatado);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteCategoriaByUuid(UUID.fromString(uuid));
    }
}
