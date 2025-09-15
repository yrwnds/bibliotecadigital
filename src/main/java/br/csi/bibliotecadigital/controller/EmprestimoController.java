package br.csi.bibliotecadigital.controller;


import br.csi.bibliotecadigital.model.emprestimo.Emprestimo;
import br.csi.bibliotecadigital.model.usuario.Usuario;
import br.csi.bibliotecadigital.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
@Tag(name = "Emprestimos", description = "Path relacionado a operações de empréstimos.")
public class EmprestimoController {
    private EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/listar")
    public List<Emprestimo> listar(){
        return this.emprestimoService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar empréstimo por ID", description = "Retorna um empréstimo correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimo encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado", content = @Content)
    })
    public Emprestimo emprestimo(@PathVariable long id){
        return this.emprestimoService.buscarPorId(id);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    @Transactional
    @Operation(summary = "Criar um novo empréstimo", description = "Cria um novo empréstimo e o adiciona à lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empréstimo criado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    public ResponseEntity salvar(@RequestBody @Valid Emprestimo emprestimo, UriComponentsBuilder uriBuilder){
        this.emprestimoService.salvar(emprestimo);
        URI uri = uriBuilder.path("/emprestimos/{uuid}").buildAndExpand(emprestimo.getUuid()).toUri();
        return ResponseEntity.created(uri).body(emprestimo);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid Emprestimo emprestimo){
        this.emprestimoService.atualizar(emprestimo);
        return ResponseEntity.ok(emprestimo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable long id){
        this.emprestimoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/uuid/{uuid}")
    public Emprestimo emprestimo(@PathVariable String uuid){
        return this.emprestimoService.getEmprestimoUuid(uuid);
    }

    @PutMapping("/uuid")
    public void atualizarUuid(@RequestBody @Valid Emprestimo emprestimo){
        this.emprestimoService.atualizar(emprestimo);
    }
}
