package br.csi.bibliotecadigital;

import br.csi.bibliotecadigital.model.categoria.Categoria;
import br.csi.bibliotecadigital.model.categoria.CategoriaRepository;
import br.csi.bibliotecadigital.model.livro.Livro;
import br.csi.bibliotecadigital.model.livro.LivroRepository;
import br.csi.bibliotecadigital.model.usuario.Usuario;
import br.csi.bibliotecadigital.model.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
		info = @Info(
				title = "API Bibliodigital",
				version = "1.0",
				description = "Documentação da API biblioteca digital.",
				contact = @Contact(name = "Leticia Nunes", email = "nunes.leticia@acad.ufsm.br")
		)
)

@SpringBootApplication
public class BibliotecadigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecadigitalApplication.class, args);
	}

/*	@Bean
	public CommandLineRunner demo(UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository, LivroRepository livroRepository) {
		return (args) ->{
			usuarioRepository.save(new Usuario(
					"Jaqueline Pereira", "jacque.p@gmail.com", "98765432@Jp", "88888888")
			);
		};
	} */

/*	@Bean
	public FlywayMigrationStrategy cleanMigrateStrategy() {
		return flyway -> {
			flyway.repair();
			flyway.migrate();
		};
	} */

}

