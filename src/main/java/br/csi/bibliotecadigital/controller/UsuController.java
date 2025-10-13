package br.csi.bibliotecadigital.controller;


import br.csi.bibliotecadigital.model.usuario.DadosUsuario;
import br.csi.bibliotecadigital.model.usuario.Usuario;
import br.csi.bibliotecadigital.service.UsuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Path relacionado a operações de usuários.")
public class UsuController {
    private UsuService usuarioService;

    public UsuController(UsuService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public List<DadosUsuario> listar() {
        return this.usuarioService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário correspondente ao ID fornecido.")
    public DadosUsuario usuario(@Parameter(description = "ID do usuário a ser buscado") @PathVariable long id) {
        return this.usuarioService.buscarPorId(id);
    }

    @GetMapping("buscaremail/{email}")
    public DadosUsuario buscarporEmail(@PathVariable @Email String email){
        return this.usuarioService.buscarPorEmail(email);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json) {
        System.out.println(json);
    }

    @PostMapping()
    @Operation(summary = "Criar um novo usuário", description = "Cria um novo usuário e o adiciona à lista")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder) {
        this.usuarioService.cadastrar(usuario);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping
    @Operation(summary = "Atualizar um usuário existente", description = "Atualiza um usuário.")
    public ResponseEntity atualizar(@RequestBody @Valid Usuario usuario) {
        this.usuarioService.atualizar(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable long id) {
        this.usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/uuid/{uuid}")
    public Usuario usuario(@PathVariable String uuid) {
        return this.usuarioService.getUsuarioUuid(uuid);
    }

    @PutMapping("/uuid")
    public void atualizarUuid(@RequestBody @Valid Usuario usuario) {
        this.usuarioService.atualizar(usuario);
    }
}
