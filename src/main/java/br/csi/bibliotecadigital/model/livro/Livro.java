package br.csi.bibliotecadigital.model.livro;

import br.csi.bibliotecadigital.model.categoria.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidade que representa um livro da biblioteca digital no sistema.")
public class Livro {

    @UuidGenerator
    private UUID uuid;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long isbn;

    @NonNull
    @NotBlank
    private String titulo;
    @NonNull
    @NotBlank
    private String autor;
    @NonNull
    private long anopublicado;

    @NonNull
    @NotBlank
    private String linguagem;

    @NonNull
    private long n_exemplares;
    @NonNull
    private long n_disponivel;

    @ManyToOne
    @NonNull
    private Categoria categoria;
}
