package br.csi.bibliotecadigital.service;

import br.csi.bibliotecadigital.model.usuario.DadosUsuario;
import br.csi.bibliotecadigital.model.usuario.Usuario;
import br.csi.bibliotecadigital.model.usuario.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuService {
    private final UsuarioRepository repository;
    public UsuService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        this.repository.save(usuario);
    }

    public List<DadosUsuario> listar() {
        return this.repository.findAll().stream().map(DadosUsuario::new).toList();
    }

    public DadosUsuario buscarPorId(Long id) {
        Usuario usuario = this.repository.findById(id).get();
        return new DadosUsuario(usuario);
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

    public DadosUsuario buscarPorEmail(String email) {
        Usuario usuario = this.repository.findUsuarioByEmailContainsIgnoreCase(email);
        return new DadosUsuario(usuario);
    }

    public Usuario getUsuarioUuid(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findUsuarioByUuid(uuidformatado);
    }

    public void deletarUUID(String uuid) {
        this.repository.deleteUsuarioByUuid(UUID.fromString(uuid));
    }
}
