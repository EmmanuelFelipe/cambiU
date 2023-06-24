package br.unibh.sdm.cambiu.backendcliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.unibh.sdm.cambiu.backendcliente.persistencia")
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);


	public static void main(String[] args) {
		log.info("Inicializando...");
		System.setProperty("server.servlet.context-path", "/cliente");
		System.setProperty("server.port", "8080");
		new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
	}

}
