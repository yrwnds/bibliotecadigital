package br.csi.bibliotecadigital.service;

import br.csi.bibliotecadigital.model.usuario.Usuario;
import br.csi.bibliotecadigital.model.usuario.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository repository;

    public AutenticacaoService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException{
        Usuario usuario = this.repository.findUsuarioByMatricula(matricula);
        if(usuario == null){
            return null;
        }
        else{
            UserDetails user = User.withUsername(usuario.getMatricula()).password(usuario.getSenha()).authorities(usuario.getIdentificador()).build();
            return user;
        }
    }
}
