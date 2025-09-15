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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
    })
    public Categoria categoria(@PathVariable long id){
        return this.categoriaService.buscarPorId(id);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    @Transactional
    @Operation(summary = "Criar uma nova categoria", description = "Cria uma nova categoria e a adiciona à lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
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
