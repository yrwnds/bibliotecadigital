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

    @GetMapping("/buscar/{usuid}")
    public List<Emprestimo> buscarUsuId(@PathVariable long usuid){
        return this.emprestimoService.buscarPorUsuarioId(usuid);
    }

    @GetMapping("/buscar/{livroid}")
    public List<Emprestimo> buscarLivroid(@PathVariable long livroid){
        return this.emprestimoService.buscarPorLivroId(livroid);
    }

    @GetMapping("/buscar/{datapego}")
    public List<Emprestimo> buscarData(@PathVariable String datapego){
        return this.emprestimoService.buscarPorDatapego(String.valueOf(datapego));
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
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
