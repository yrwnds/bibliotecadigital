package br.csi.bibliotecadigital.model.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public Categoria findCategoriaByUuid(UUID uuid);
    public void deleteCategoriaByUuid(UUID uuid);

    public Categoria findCategoriaByNome(String nome);
}
