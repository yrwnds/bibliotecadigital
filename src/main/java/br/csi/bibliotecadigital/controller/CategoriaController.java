package br.csi.bibliotecadigital.controller;

import br.csi.bibliotecadigital.model.categoria.Categoria;
import br.csi.bibliotecadigital.model.usuario.Usuario;
import br.csi.bibliotecadigital.service.CategoriaService;
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
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Path relacionado a operações de categorias.")
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
    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria correspondente ao ID fornecido.")
    public Categoria categoria(@PathVariable long id){
        return this.categoriaService.buscarPorId(id);
    }

    @GetMapping("/buscar/{nome}")
    public Categoria buscarNome(@PathVariable String nome){
        return this.categoriaService.buscarPorNome(nome);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    @Transactional
    @Operation(summary = "Criar uma nova categoria", description = "Cria uma nova categoria e a adiciona à lista")
    public ResponseEntity salvar(@RequestBody @Valid Categoria categoria, UriComponentsBuilder uriBuilder){
        this.categoriaService.salvar(categoria);
        URI uri = uriBuilder.path("/categorias/{uuid}").buildAndExpand(categoria.getUuid()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @GetMapping("/uuid/{uuid}")
    public Categoria categoria(@PathVariable String uuid){
        return this.categoriaService.getCategoriaUuid(uuid);
    }

}
