package br.unibh.sdm.cambiu.backendmoeda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.regions.Regions;

import br.unibh.sdm.cambiu.backendmoeda.entidades.Moeda;
import br.unibh.sdm.cambiu.backendmoeda.persistencia.MoedaRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class MoedaTests {

    private static Logger LOGGER = LoggerFactory.getLogger(MoedaTests.class);

    @Configuration
    @EnableDynamoDBRepositories(basePackageClasses = MoedaRepository.class)
    public static class DynamoDBConfig {
        @Value("${amazon.aws.accesskey}")
        private String amazonAWSAccessKey;

        @Value("${amazon.aws.secretkey}")
        private String amazonAWSSecretKey;

        public AWSCredentialsProvider amazonAWSCredentialsProvider() {
            return new AWSStaticCredentialsProvider(amazonAWSCredentials());
        }

        @Bean
        public AWSCredentials amazonAWSCredentials() {
            return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
        }

        @Bean
        public AmazonDynamoDB amazonDynamoDB() {
            return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
                    .withRegion(Regions.US_EAST_1).build();
        }
    }

    @Autowired
    private MoedaRepository repository;

    @Test
    @Order(1)
    public void teste1Criacao() {
        LOGGER.info("Criando objetos...");
        Moeda m1 = new Moeda();
        m1.setCodigo("USD");
        m1.setNome("Dólar Americano");
        m1.setSigla("US");
        m1.setDescricao("Moeda dos Estados Unidos");
        repository.save(m1);

        Moeda m2 = new Moeda();
        m2.setCodigo("EUR");
        m2.setNome("Euro");
        m2.setSigla("EU");
        m2.setDescricao("Moeda da União Europeia");
        repository.save(m2);

        LOGGER.info("Pesquisando todos os objetos");
        Iterable<Moeda> lista = repository.findAll();
        assertNotNull(lista.iterator());
        for (Moeda moeda : lista) {
            LOGGER.info(moeda.toString());
        }

        LOGGER.info("Pesquisando um objeto");
        List<Moeda> result = repository.findByNome("Dólar Americano");
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getCodigo(), "USD");
        LOGGER.info("Encontrado: {}", result.get(0));
    }

    @Test
    @Order(2)
    public void teste2Listagem() {
        LOGGER.info("Listando todos os objetos...");
        Iterable<Moeda> lista = repository.findAll();
        assertNotNull(lista.iterator());
        int contador = 0;
        for (Moeda moeda : lista) {
            LOGGER.info(moeda.toString());
            contador++;
        }
        assertTrue(contador > 0);
        LOGGER.info("A listagem foi realizada com sucesso");
    }

    @Test
    @Order(3)
    public void teste2Exclusao() {
        LOGGER.info("Excluindo objetos...");
        Moeda m1 = repository.findByCodigo("USD").get(0);
        repository.delete(m1);

        Moeda m2 = repository.findByCodigo("EUR").get(0);
        repository.delete(m2);

        List<Moeda> result = repository.findByNome("Dólar Americano");
        assertEquals(result.size(), 0);
        LOGGER.info("Exclusão feita com sucesso");
    }


}
