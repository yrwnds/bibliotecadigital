package br.csi.bibliotecadigital.model.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public Categoria findCategoriaByUuid(UUID uuid);
    public Categoria deleteCategoriaByUuid(UUID uuid);
}
