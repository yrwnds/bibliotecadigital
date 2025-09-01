package br.csi.bibliotecadigital.model.livro;

import br.csi.bibliotecadigital.model.categoria.Categoria;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name="livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Livro {

    @UuidGenerator
    private UUID uuid;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String titulo;
    @NonNull
    private String autor;
    @NonNull
    private long anopublicado;

    @NonNull
    private String linguagem;

    @NonNull
    private long n_exemplares;
    @NonNull
    private long n_disponivel;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @NonNull
    private Categoria categoria;
}
