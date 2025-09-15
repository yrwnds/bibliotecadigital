package br.csi.bibliotecadigital.model.livro;

import br.csi.bibliotecadigital.model.categoria.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String titulo;
    @NonNull
    @NotBlank
    private String autor;
    @NonNull
    @NotBlank
    private long anopublicado;

    @NonNull
    @NotBlank
    private String linguagem;

    @NonNull
    @NotBlank
    private long n_exemplares;
    @NonNull
    @NotBlank
    private long n_disponivel;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @NonNull
    @NotBlank
    private Categoria categoria;
}
