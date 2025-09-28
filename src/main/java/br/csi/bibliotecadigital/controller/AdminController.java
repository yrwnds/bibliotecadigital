package br.csi.bibliotecadigital.controller;


import br.csi.bibliotecadigital.model.administrador.Administrador;
import br.csi.bibliotecadigital.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admins")
@Tag(name = "Admins", description = "Path relacionado a operações de administradores.")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/listar")
    public List<Administrador> listar(){
        return this.adminService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar admin por ID", description = "Retorna um admin correspondente ao ID fornecido.")
    public Administrador administrador(@PathVariable long id){
        return this.adminService.buscarPorId(id);
    }

    @GetMapping("/{email}")
    public Administrador buscarporEmail(@PathVariable @Email String email){
        return this.adminService.buscarPorEmail(email);
    }

    @GetMapping("/{email}/{senha}")
    public Administrador buscarporEmailSenha(@PathVariable @Email String email, @PathVariable String senha){
        return this.adminService.buscarPorEmaileSenha(email, senha);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    @Transactional
    @Operation(summary = "Criar um novo admin", description = "Cria um novo admin e o adiciona à lista")
    public ResponseEntity salvar(@RequestBody @Valid Administrador administrador, UriComponentsBuilder uriBuilder){
        this.adminService.salvar(administrador);
        URI uri = uriBuilder.path("/admins/{uuid}").buildAndExpand(administrador.getUuid()).toUri();
        return ResponseEntity.created(uri).body(administrador);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid Administrador administrador){
        this.adminService.atualizar(administrador);
        return ResponseEntity.ok(administrador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable long id){
        this.adminService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/uuid/{uuid}")
    public Administrador administrador(@PathVariable String uuid){
        return this.adminService.getAdministradorUuid(uuid);
    }

    @PutMapping("/uuid")
    public void atualizarUuid(@RequestBody @Valid Administrador administrador){
        this.adminService.atualizar(administrador);
    }
}
