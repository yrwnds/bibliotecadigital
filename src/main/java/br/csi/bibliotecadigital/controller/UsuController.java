package br.csi.bibliotecadigital.controller;


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
    public List<Usuario> listar() {
        return this.usuarioService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário correspondente ao ID fornecido.")
    public Usuario usuario(@Parameter(description = "ID do usuário a ser buscado") @PathVariable long id) {
        return this.usuarioService.buscarPorId(id);
    }

    @GetMapping("buscarcredentials/{email}")
    public Usuario buscarporEmail(@PathVariable @Email String email){
        return this.usuarioService.buscarPorEmail(email);
    }

    @GetMapping("buscarcredentials/{matricula}/{senha}")
    public Usuario buscarporEmailSenha(@PathVariable String matricula, @PathVariable String senha){
        return this.usuarioService.buscarPorMateSenha(matricula, senha);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json) {
        System.out.println(json);
    }

    @PostMapping()
    @Operation(summary = "Criar um novo usuário", description = "Cria um novo usuário e o adiciona à lista")
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder) {
        this.usuarioService.salvar(usuario);
        URI uri = uriBuilder.path("/usuarios/{uuid}").buildAndExpand(usuario.getUuid()).toUri();
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
