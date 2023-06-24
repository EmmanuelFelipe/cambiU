package br.unibh.sdm.cambiu.backendmoeda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.unibh.sdm.cambiu.backendmoeda.entidades.Cotacao;
import br.unibh.sdm.cambiu.backendmoeda.persistencia.CotacaoRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class CotacaoTests {

    private static Logger LOGGER = LoggerFactory.getLogger(CotacaoTests.class);

    @Autowired
    private CotacaoRepository repository;

    @Test
    @Order(1)
    public void teste1Criacao() {
        LOGGER.info("Criando objetos...");
        Cotacao c1 = new Cotacao();
        c1.setCodigo("123");
        c1.setOrigem("USD");
        c1.setDestino("BRL");
        c1.setNome("Dolar para Real");
        c1.setCompra(5.20);
        c1.setVenda(5.30);
        c1.setDataConsulta("2023-06-24");
        c1.setDataCriacao(LocalDateTime.now());
        repository.save(c1);

        Cotacao c2 = new Cotacao();
        c2.setCodigo("456");
        c2.setOrigem("EUR");
        c2.setDestino("BRL");
        c2.setNome("Euro para Real");
        c2.setCompra(6.10);
        c2.setVenda(6.20);
        c2.setDataConsulta("2023-06-24");
        c2.setDataCriacao(LocalDateTime.now());
        repository.save(c2);

        LOGGER.info("Pesquisando todos os objetos");
        Iterable<Cotacao> lista = repository.findAll();
        assertNotNull(lista.iterator());
        for (Cotacao cotacao : lista) {
            LOGGER.info(cotacao.toString());
        }

        LOGGER.info("Pesquisando um objeto");
        List<Cotacao> result = repository.findByNome("Dolar para Real");
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getCodigo(), "123");
        LOGGER.info("Encontrado: {}", result.get(0));
    }

    @Test
    @Order(2)
    public void teste2Listagem() {
        LOGGER.info("Listando todos os objetos...");
        Iterable<Cotacao> lista = repository.findAll();
        assertNotNull(lista.iterator());
        int contador = 0;
        for (Cotacao cotacao : lista) {
            LOGGER.info(cotacao.toString());
            contador++;
        }
        assertTrue(contador > 0);
        LOGGER.info("A listagem foi realizada com sucesso");
    }

    @Test
    @Order(3)
    public void teste3Exclusao() {
        LOGGER.info("Excluindo objetos...");
        Cotacao c1 = repository.findByCodigo("123").get(0);
        repository.delete(c1);

        List<Cotacao> result = repository.findByNome("Dolar para Real");
        assertEquals(result.size(), 0);
        LOGGER.info("Exclusão feita com sucesso");
    }
}