package br.csi.bibliotecadigital.model.usuario;

public record DadosUsuario(Long id, String matricula, String identificador, String email, String nome, String senha, String imagePath) {

    public DadosUsuario(Usuario usuario){
        this(usuario.getId(),usuario.getMatricula(),usuario.getIdentificador(), usuario.getEmail(), usuario.getNome(), usuario.getSenha(), usuario.getImagem_path());
    }

}
