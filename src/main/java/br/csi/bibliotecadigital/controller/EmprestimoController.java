package br.csi.bibliotecadigital.controller;


import br.csi.bibliotecadigital.model.emprestimo.Emprestimo;
import br.csi.bibliotecadigital.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
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
    public Emprestimo emprestimo(@PathVariable long id){
        return this.emprestimoService.buscarPorId(id);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    public void salvar(@RequestBody Emprestimo emprestimo){
        this.emprestimoService.salvar(emprestimo);
    }

    @PutMapping
    public void atualizar(@RequestBody Emprestimo emprestimo){
        this.emprestimoService.atualizar(emprestimo);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable long id){
        this.emprestimoService.excluir(id);
    }

    @GetMapping("/uuid/{uuid}")
    public Emprestimo emprestimo(@PathVariable String uuid){
        return this.emprestimoService.getEmprestimoUuid(uuid);
    }

    @PutMapping("/uuid")
    public void atualizarUuid(@RequestBody Emprestimo emprestimo){
        this.emprestimoService.atualizar(emprestimo);
    }
}
