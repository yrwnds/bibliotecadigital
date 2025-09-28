package br.csi.bibliotecadigital.service;

import br.csi.bibliotecadigital.model.emprestimo.Emprestimo;
import br.csi.bibliotecadigital.model.emprestimo.EmprestimoRepository;
import br.csi.bibliotecadigital.model.livro.Livro;
import br.csi.bibliotecadigital.model.livro.LivroRepository;
import br.csi.bibliotecadigital.model.usuario.Usuario;
import br.csi.bibliotecadigital.model.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EmprestimoService {
    private final EmprestimoRepository repository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroService livroService;

    public EmprestimoService(EmprestimoRepository repository, LivroRepository livroRepository, UsuarioRepository usuarioRepository, LivroService livroService) {
        this.repository = repository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroService = livroService;
    }

    public void salvar(Emprestimo emprestimo) {
        this.repository.save(emprestimo);
    }

    public List<Emprestimo> listar() {
        return this.repository.findAll();
    }

    public List<Emprestimo> buscarPorDatapego(String data) {
        Timestamp dataTimestamp = Timestamp.valueOf(data);
        System.out.println(dataTimestamp);
        return this.repository.findEmprestimoByDatapego(dataTimestamp);
    }

    public List<Emprestimo> buscarPorLivroId(long isbn){
        return this.repository.findEmprestimoByLivroIsbn(isbn);
    }

    public List<Emprestimo> buscarPorUsuarioId(long id){
        return this.repository.findEmprestimoByUsuarioId(id);
    }

    public Emprestimo buscarPorUsuarioIdeLivroIsbn(long id, long isbn){
        return this.repository.findEmprestimoByLivroIsbnAndUsuarioId(id, isbn);
    }

    public Emprestimo buscarPorId(Long id) {
        return this.repository.findById(id).get();
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Emprestimo emprestimo) {
        Emprestimo e = this.repository.getReferenceById(emprestimo.getId());
        e.setDatapego(emprestimo.getDatapego());
        e.setDataprazo(emprestimo.getDataprazo());
        e.setLivro(emprestimo.getLivro());
        e.setUsuario(emprestimo.getUsuario());
        e.setStatus(emprestimo.getStatus());
        this.repository.save(e);
    }

    public Emprestimo getEmprestimoUuid(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findEmprestimoByUuid(uuidformatado);
    }

    @Transactional
    public Emprestimo pegarLivro(Long ISBN, Long usuId){
        Livro livro = this.livroRepository.findById(usuId).get();
        Usuario usuario = this.usuarioRepository.findById(ISBN).get();

        if(livro.getN_disponivel()==0){
            throw new RuntimeException("Livro nao esta disponivel para pegar emprestado.");
        }

        List<Emprestimo> checar = this.repository.findAll();
        for(Emprestimo emprestimo : checar){
            if(emprestimo.getUsuario().equals(usuario) && emprestimo.getStatus().equals("ATIVO")){
                throw new RuntimeException("Este usuario ja pegou este livro emprestado.");
            }
        }

        livro.setN_disponivel(livro.getN_disponivel()-1);

        this.livroService.atualizar(livro);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setStatus("ATIVO");

        LocalDateTime dataPego = LocalDateTime.now();
        LocalDateTime dataPrazo = dataPego.plusDays(7);

        System.out.println("DATA PEGO: " + dataPego + "/n DATA PRAZO: " + dataPrazo);

        emprestimo.setDatapego(Timestamp.valueOf(dataPego));
        emprestimo.setDataprazo(Timestamp.valueOf(dataPrazo));

        return this.repository.save(emprestimo);
    }

    @Transactional
    public Emprestimo devolverLivro(Long ISBN, Long usuId){
        Livro livro = this.livroRepository.findById(ISBN).get();

        Emprestimo emprestimo = this.repository.findEmprestimoByLivroIsbnAndUsuarioId(ISBN, usuId);
        if(emprestimo.getStatus().equals("INATIVO")){
            throw new RuntimeException("Empréstimo já se encontra inativo.");
        }

        livro.setN_disponivel(livro.getN_disponivel()+1);
        this.livroService.atualizar(livro);

        emprestimo.setStatus("INATIVO");

        return this.repository.save(emprestimo);
    }

    @Transactional
    public Emprestimo atualizarEmprestimo(Long ISBN, Long usuID){

        Emprestimo emprestimo = this.repository.findEmprestimoByLivroIsbnAndUsuarioId(ISBN, usuID);

        LocalDateTime novaDataPrazo = emprestimo.getDataprazo().toLocalDateTime().plusDays(7);;

        emprestimo.setDataprazo(Timestamp.valueOf(novaDataPrazo));
        return this.repository.save(emprestimo);

    }
}
