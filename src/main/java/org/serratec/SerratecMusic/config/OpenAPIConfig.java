package org.serratec.SerratecMusic.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl("http://localhost:8080");
		devServer.setDescription("Servidor Local");

		Contact contato = new Contact();
		contato.setEmail("nathanmlf@hotmail.com");
		contato.setName("Nathan Mendonça Lourenço Ferreira");
		contato.setUrl("https://www.linkedin.com/in/nathanmlf/");

		License apacheLicense = new License().name("Apache License Version 2.0")
				.url("https://www.apache.org/licenses/LICENSE-2.0");

		Info info = new Info().title("API - Compra de produtos").version("1.0").contact(contato)
				.description("API desenvolvida para controle de um sistema de compras de produtos.")
				.termsOfService("https://github.com/nathanmlf").license(apacheLicense);

		return new OpenAPI().info(info).servers(List.of(devServer));
	}
}
