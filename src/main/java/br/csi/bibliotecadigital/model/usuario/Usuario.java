package br.csi.bibliotecadigital.model.usuario;

import br.csi.bibliotecadigital.model.categoria.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa um usuário da biblioteca digital no sistema.")
public class Usuario {

    @UuidGenerator
    private UUID uuid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do usuário", example = "1")
    private long id;

    @NonNull
    @NotBlank
    @Schema(description = "Nome do usuário", example = "Felipe Santos")
    private String nome;

    @NonNull
    @NotBlank
    @Email
    @Schema(description = "E-mail do usuário", example = "exemplo@email.com")
    private String email;

    @NonNull
    @NotBlank
    private String senha;

    @NonNull
    @NotBlank
    @Pattern(regexp = "^\\d{8}$")
    @Schema(description = "Matricula do usuário", example = "12345678")
    private String matricula;
}
