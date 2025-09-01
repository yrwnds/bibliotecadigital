package br.csi.bibliotecadigital.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findUsuarioByUuid(UUID uuid);
    public void deleteUsuarioByUuid(UUID uuid);
}
