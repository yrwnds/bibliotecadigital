package br.csi.bibliotecadigital.service;

import br.csi.bibliotecadigital.model.livro.Livro;
import br.csi.bibliotecadigital.model.livro.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LivroService {

    private final LivroRepository repository;
    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public void salvar(Livro livro) {
        this.repository.save(livro);
    }

    public List<Livro> listar() {
        return this.repository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return this.repository.findById(id).get();
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Livro livro) {
        Livro l = this.repository.getReferenceById(livro.getId());
        l.setTitulo(livro.getTitulo());
        l.setAutor(livro.getAutor());
        l.setAnopublicado(livro.getAnopublicado());
        l.setLinguagem(livro.getLinguagem());
        l.setN_exemplares(livro.getN_exemplares());
        l.setN_disponivel(livro.getN_disponivel());
        l.setCategoria(livro.getCategoria());
        this.repository.save(l);
    }

    public void atualizarUUID(Livro livro){
        Livro l = this.repository.findLivroByUuid(livro.getUuid());
        l.setAutor(livro.getAutor());
        l.setAnopublicado(livro.getAnopublicado());
        l.setLinguagem(livro.getLinguagem());
        l.setN_exemplares(livro.getN_exemplares());
        l.setN_disponivel(livro.getN_disponivel());
        l.setAutor(l.getAutor());
        l.setCategoria(l.getCategoria());
        this.repository.save(l);
    }

    public Livro getLivroUuid(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findLivroByUuid(uuidformatado);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteLivroByUuid(UUID.fromString(uuid));
    }
}
