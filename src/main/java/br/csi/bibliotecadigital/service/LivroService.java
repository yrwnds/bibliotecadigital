package br.csi.bibliotecadigital.service;

import br.csi.bibliotecadigital.model.livro.Livro;
import br.csi.bibliotecadigital.model.livro.LivroRepository;

import org.springframework.data.domain.Sort;
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

    public List<Livro> listarTitulo() {
        return this.repository.findAll(Sort.by("titulo").ascending());
    }

    public List<Livro> listarAutor() {
        return this.repository.findAll(Sort.by("autor").ascending());
    }

    public List<Livro> listarAnoAsc() {
        return this.repository.findAll(Sort.by("anopublicado").ascending());
    }

    public List<Livro> listarAnoDesc() {
        return this.repository.findAll(Sort.by("anopublicado").descending());
    }

    public Livro buscarPorId(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Livro livro) {
        Livro l = this.repository.getReferenceById(livro.getIsbn());
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

    public List<Livro> getLivroTitulo(String titulo){
        return this.repository.findLivroByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> getLivroAutor(String autor){
        return this.repository.findLivroByAutorContainingIgnoreCase(autor);
    }

    public List<Livro> getLivroCategoria(String categoria){
        return this.repository.findLivroByCategoriaNomeContainingIgnoreCase(categoria);
    }

    public List<Livro> getLivroLinguagem(String linguagem){
        return this.repository.findLivroByLinguagemContainingIgnoreCase(linguagem);
    }

    public List<Livro> getLivroDisponiveis(){
        return this.repository.findLivroByN_disponivelGreaterThan0();
    }

    public List<Livro> getLivro(String search){
        return this.repository.findLivroByAnyParam(search);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteLivroByUuid(UUID.fromString(uuid));
    }
}
