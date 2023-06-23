package br.unibh.sdm.cambiu.backendmoeda;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/moeda-api");
		new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
	}

}
