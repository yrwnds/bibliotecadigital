package br.csi.bibliotecadigital.service;

import br.csi.bibliotecadigital.model.usuario.Usuario;
import br.csi.bibliotecadigital.model.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        u.setQt_livros_emprestados(usuario.getQt_livros_emprestados());
        u.setIdentificador(usuario.getIdentificador());
        this.repository.save(u);
    }

    public Usuario buscarPorEmail(String email) {
        return this.repository.findUsuarioByEmail(email);
    }

    public Usuario buscarPorMateSenha(String matricula, String senha) {
        return this.repository.findUsuarioByMatriculaAndSenha(matricula, senha);
    }

    public Usuario getUsuarioUuid(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findUsuarioByUuid(uuidformatado);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteUsuarioByUuid(UUID.fromString(uuid));
    }
}
