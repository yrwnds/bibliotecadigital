package br.csi.bibliotecadigital.service;

import br.csi.bibliotecadigital.model.usuario.Usuario;
import br.csi.bibliotecadigital.model.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuService {
    private final UsuarioRepository repository;
    public UsuService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvar(Usuario usuario) {
        this.repository.save(usuario);
    }

    public List<Usuario> listar() {
        return this.repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return this.repository.findById(id).get();
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Usuario usuario) {
        Usuario u = this.repository.getReferenceById(usuario.getId());
        u.setNome(usuario.getNome());
        u.setEmail(usuario.getEmail());
        u.setSenha(usuario.getSenha());
        u.setMatricula(usuario.getMatricula());
        this.repository.save(u);
    }

    public void atualizarUUID(Usuario usuario){
        Usuario u = this.repository.findUsuarioByUuid(usuario.getUuid());
        u.setNome(usuario.getNome());
        u.setEmail(usuario.getEmail());
        u.setSenha(usuario.getSenha());
        u.setMatricula(usuario.getMatricula());
        this.repository.save(u);
    }

    public Usuario getUsuarioUuid(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findUsuarioByUuid(uuidformatado);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteUsuarioByUuid(UUID.fromString(uuid));
    }
}
