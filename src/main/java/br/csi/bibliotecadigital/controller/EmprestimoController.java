package br.csi.bibliotecadigital.controller;


import br.csi.bibliotecadigital.model.emprestimo.Emprestimo;
import br.csi.bibliotecadigital.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
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
    public Emprestimo emprestimo(@PathVariable long id){
        return this.emprestimoService.buscarPorId(id);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity criardoZero(@RequestBody @Valid Emprestimo emprestimo, UriComponentsBuilder uriBuilder){
        this.emprestimoService.salvar(emprestimo);
        URI uri = uriBuilder.path("/emprestimos/{uuid}").buildAndExpand(emprestimo.getUuid()).toUri();
        return ResponseEntity.created(uri).body(emprestimo);
    }

    @PostMapping("/emprestar/{isbn}/{usuid}")
    public ResponseEntity<Emprestimo> emprestar(@PathVariable long isbn, @PathVariable long usuid){
        Emprestimo emprestimo = emprestimoService.pegarLivro(isbn, usuid);
        return ResponseEntity.ok(emprestimo);
    }

    @PostMapping("/devolver/{isbn}/{usuid}")
    public ResponseEntity<Emprestimo> devolver(@PathVariable long isbn, @PathVariable long usuid){
        Emprestimo emprestimo = emprestimoService.devolverLivro(isbn, usuid);
        return ResponseEntity.ok(emprestimo);
    }

    @PostMapping("/atualizar/{isbn}/{usuid}")
    public ResponseEntity<Emprestimo> atualizar(@PathVariable long isbn, @PathVariable long usuid){
        Emprestimo emprestimo = emprestimoService.atualizarEmprestimo(isbn, usuid);
        return ResponseEntity.ok(emprestimo);
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
