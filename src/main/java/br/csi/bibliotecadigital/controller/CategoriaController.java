package br.csi.bibliotecadigital.controller;

import br.csi.bibliotecadigital.model.categoria.Categoria;
import br.csi.bibliotecadigital.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    public List<Categoria> listar(){
        return this.categoriaService.listar();
    }

    @GetMapping("/{id}")
    public Categoria categoria(@PathVariable long id){
        return this.categoriaService.buscarPorId(id);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    public void salvar(@RequestBody Categoria categoria){
        this.categoriaService.salvar(categoria);
    }
    
    @GetMapping("/uuid/{uuid}")
    public Categoria categoria(@PathVariable String uuid){
        return this.categoriaService.getCategoriaUuid(uuid);
    }

}
