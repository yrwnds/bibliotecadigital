package br.csi.bibliotecadigital.model.emprestimo;

import br.csi.bibliotecadigital.model.livro.Livro;
import br.csi.bibliotecadigital.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="emprestimo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Emprestimo {

    @UuidGenerator
    private UUID uuid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "isbn_id")
    @NonNull
    private Livro ISBN;

    @ManyToOne
    @JoinColumn(name = "usu_id_id")
    @NonNull
    private Usuario usu_id;

    @NonNull
    private Timestamp datapego;

    @NonNull
    private Timestamp dataprazo;

    @NonNull
    private String status;
}
