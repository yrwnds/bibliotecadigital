package br.csi.bibliotecadigital.controller;


import br.csi.bibliotecadigital.model.administrador.Administrador;
import br.csi.bibliotecadigital.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
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
    public Administrador administrador(@PathVariable long id){
        return this.adminService.buscarPorId(id);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    public void salvar(@RequestBody Administrador administrador){
        this.adminService.salvar(administrador);
    }

    @PutMapping
    public void atualizar(@RequestBody Administrador administrador){
        this.adminService.atualizar(administrador);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable long id){
        this.adminService.excluir(id);
    }

    @GetMapping("/uuid/{uuid}")
    public Administrador administrador(@PathVariable String uuid){
        return this.adminService.getAdministradorUuid(uuid);
    }

    @PutMapping("/uuid")
    public void atualizarUuid(@RequestBody Administrador administrador){
        this.adminService.atualizar(administrador);
    }
}
