package br.csi.bibliotecadigital.controller;

import br.csi.bibliotecadigital.model.livro.Livro;
import br.csi.bibliotecadigital.model.usuario.Usuario;
import br.csi.bibliotecadigital.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livros")
@Tag(name = "Livros", description = "Path relacionado a operações de livros.")
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
    @Operation(summary = "Buscar livro por ID", description = "Retorna um livro correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "500", description = "Livro não encontrado", content = @Content)
    })
    public Livro livro(@PathVariable long id){
        return this.livroService.buscarPorId(id);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    @Transactional
    @Operation(summary = "Criar um novo livro", description = "Cria um novo livro e o adiciona à lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    public ResponseEntity salvar(@RequestBody @Valid Livro livro, UriComponentsBuilder uriBuilder){
        this.livroService.salvar(livro);
        URI uri = uriBuilder.path("/livros/{uuid}").buildAndExpand(livro.getUuid()).toUri();
        return ResponseEntity.created(uri).body(livro);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid Livro livro){
        this.livroService.atualizar(livro);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable long id){
        this.livroService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/uuid/{uuid}")
    public Livro livro(@PathVariable String uuid){
        return this.livroService.getLivroUuid(uuid);
    }

    @PutMapping("/uuid")
    public void atualizarUuid(@RequestBody @Valid Livro livro){
        this.livroService.atualizar(livro);
    }

}
