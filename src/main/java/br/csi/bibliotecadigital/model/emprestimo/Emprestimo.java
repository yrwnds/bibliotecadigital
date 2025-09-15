package br.csi.bibliotecadigital.model.emprestimo;

import br.csi.bibliotecadigital.model.livro.Livro;
import br.csi.bibliotecadigital.model.usuario.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Entidade que representa um empréstimo da biblioteca digital no sistema.")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @NonNull
    private Livro livro;

    @UuidGenerator
    private UUID uuid;

    @ManyToOne
    @NonNull
    private Usuario usuario;

    @NonNull
    @Schema(description = "Data em que o empréstimo foi realizado.", example = "YYYY-MM-DD HH:MM:SS")
    private Timestamp datapego;

    @NonNull
    @Schema(description = "Data de prazo para devolvimento. Tipicamente uma semana após o empréstimo.", example = "YYYY-MM-DD HH:MM:SS")
    private Timestamp dataprazo;

    @NonNull
    @NotBlank
    @Schema(description = "Status do empréstimo: ATIVO/INATIVO", example = "ATIVO")
    private String status;
}
