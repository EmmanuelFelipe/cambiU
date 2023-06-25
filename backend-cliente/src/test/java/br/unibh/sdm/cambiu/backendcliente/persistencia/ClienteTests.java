package br.unibh.sdm.cambiu.backendcliente.persistencia;

        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertNotNull;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.List;

        import org.junit.FixMethodOrder;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.junit.runners.MethodSorters;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;

        import br.unibh.sdm.cambiu.backendcliente.entidades.Cliente;
        import br.unibh.sdm.cambiu.backendcliente.persistencia.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTests {

    private static Logger LOGGER = LoggerFactory.getLogger(ClienteTests.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");

    @Autowired
    private ClienteRepository repository;

    @Test
    public void teste1Criacao() throws ParseException {
        LOGGER.info("Criando objetos...");
        Cliente c1 = new Cliente(null,"Maria Paula","00000000000","maria@gmail.com","31988887777",df.parse("01/01/2001"),"joao","1234");
        repository.save(c1);

        Cliente c2 = new Cliente(null,"Luan Santos","00000000001","luan@gmail.com","31988877778",df.parse("02/02/1996"),"maria","1234");
        repository.save(c2);

        Cliente c3 = new Cliente(null,"Paulo Jose","00000000002","paulo@gmail.com","31988886669",df.parse("03/03/1984"),"tiago","1234");
        repository.save(c3);

        LOGGER.info("Pesquisado todos");
        Iterable<Cliente> lista = repository.findAll();
        assertNotNull(lista.iterator());
        for (Cliente cliente : lista) {
            LOGGER.info(cliente.toString());
        }
        LOGGER.info("Pesquisado um objeto");
        List<Cliente> result = repository.findByNome("Maria Paula");
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getEmail(), "maria@gmail.com");
        LOGGER.info("Encontrado: {}", result.get(0));
    }


    @Test
    public void teste2Exclusao() throws ParseException {
        LOGGER.info("Excluindo objetos...");
        List<Cliente> result = repository.findByCpf("00000000000");
        for (Cliente cliente : result) {
            LOGGER.info("Excluindo Cliente id = "+cliente.getId());
            repository.delete(cliente);
        }
        result = repository.findByCpf("00000000001");
        for (Cliente cliente : result) {
            LOGGER.info("Excluindo Cliente id = "+cliente.getId());
            repository.delete(cliente);
        }
        result = repository.findByCpf("00000000002");
        for (Cliente cliente : result) {
            LOGGER.info("Excluindo Cliente id = "+cliente.getId());
            repository.delete(cliente);
        }
        result = repository.findByNome("Maria Paula");
        assertEquals(result.size(), 0);
        LOGGER.info("Exclusao feita com sucesso");
    }


}