package br.csi.bibliotecadigital.model.livro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    public Livro findLivroByUuid(UUID uuid);
    public void deleteLivroByUuid(UUID uuid);
}
