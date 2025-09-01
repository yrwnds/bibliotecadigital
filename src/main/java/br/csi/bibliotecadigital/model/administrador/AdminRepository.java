package br.csi.bibliotecadigital.model.administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Administrador, Long> {
    public Administrador findAdministradorByUuid(UUID uuid);
    public void deleteAdministradorByUuid(UUID uuid);
}
