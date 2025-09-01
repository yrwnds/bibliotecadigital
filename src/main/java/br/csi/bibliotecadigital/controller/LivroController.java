package br.csi.bibliotecadigital.controller;

import br.csi.bibliotecadigital.model.livro.Livro;
import br.csi.bibliotecadigital.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/listar")
    public List<Livro> listar(){
        return this.livroService.listar();
    }

    @GetMapping("/{id}")
    public Livro livro(@PathVariable long id){
        return this.livroService.buscarPorId(id);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    public void salvar(@RequestBody Livro livro){
        this.livroService.salvar(livro);
    }

    @PutMapping
    public void atualizar(@RequestBody Livro livro){
        this.livroService.atualizar(livro);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable long id){
        this.livroService.excluir(id);
    }

    @GetMapping("/uuid/{uuid}")
    public Livro livro(@PathVariable String uuid){
        return this.livroService.getLivroUuid(uuid);
    }

    @PutMapping("/uuid")
    public void atualizarUuid(@RequestBody Livro livro){
        this.livroService.atualizar(livro);
    }

}
