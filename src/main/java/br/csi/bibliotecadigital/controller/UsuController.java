package br.csi.bibliotecadigital.controller;


import br.csi.bibliotecadigital.model.usuario.Usuario;
import br.csi.bibliotecadigital.service.UsuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuController {
    private UsuService usuarioService;

    public UsuController(UsuService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public List<Usuario> listar(){
        return this.usuarioService.listar();
    }

    @GetMapping("/{id}")
    public Usuario usuario(@PathVariable long id){
        return this.usuarioService.buscarPorId(id);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    public void salvar(@RequestBody Usuario usuario){
        this.usuarioService.salvar(usuario);
    }

    @PutMapping
    public void atualizar(@RequestBody Usuario usuario){
        this.usuarioService.atualizar(usuario);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable long id){
        this.usuarioService.excluir(id);
    }

    @GetMapping("/uuid/{uuid}")
    public Usuario usuario(@PathVariable String uuid){
        return this.usuarioService.getUsuarioUuid(uuid);
    }

    @PutMapping("/uuid")
    public void atualizarUuid(@RequestBody Usuario usuario){
        this.usuarioService.atualizar(usuario);
    }
}
