package br.csi.bibliotecadigital.service;

import br.csi.bibliotecadigital.model.administrador.AdminRepository;
import br.csi.bibliotecadigital.model.administrador.Administrador;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminService {
    private final AdminRepository repository;
    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    public void salvar(Administrador administrador) {
        this.repository.save(administrador);
    }

    public List<Administrador> listar() {
        return this.repository.findAll();
    }

    public Administrador buscarPorId(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    public Administrador buscarPorEmail(String email) {
        return this.repository.findAdministradorByEmail(email);
    }

    public Administrador buscarPorEmaileSenha(String email, String senha) {
        return this.repository.findAdministradorByEmailAndSenha(email, senha);
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Administrador administrador) {
        Administrador a = this.repository.getReferenceById(administrador.getId());
        a.setNome(administrador.getNome());
        a.setEmail(administrador.getEmail());
        a.setSenha(administrador.getSenha());
        a.setMatricula(administrador.getMatricula());
        this.repository.save(a);
    }

    public void atualizarUUID(Administrador administrador){
        Administrador a = this.repository.findAdministradorByUuid(administrador.getUuid());
        a.setNome(administrador.getNome());
        a.setEmail(administrador.getEmail());
        a.setSenha(administrador.getSenha());
        a.setMatricula(administrador.getMatricula());
        this.repository.save(a);
    }

    public Administrador getAdministradorUuid(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findAdministradorByUuid(uuidformatado);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteAdministradorByUuid(UUID.fromString(uuid));
    }
}
