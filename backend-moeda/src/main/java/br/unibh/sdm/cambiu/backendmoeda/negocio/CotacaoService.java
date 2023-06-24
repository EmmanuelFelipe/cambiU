package br.unibh.sdm.cambiu.backendmoeda.negocio;

import br.unibh.sdm.cambiu.backendmoeda.entidades.Cotacao;
import br.unibh.sdm.cambiu.backendmoeda.entidades.DadosCotacao;
import br.unibh.sdm.cambiu.backendmoeda.persistencia.CotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Map;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CotacaoService {

    private final CotacaoRepository cotacaoRepository;

    @Autowired
    public CotacaoService(CotacaoRepository cotacaoRepository) {
        this.cotacaoRepository = cotacaoRepository;
    }

    public Iterable<Cotacao> getCotacoes() {
        return cotacaoRepository.findAll();
    }

    public Optional<Cotacao> getCotacaoByCodigo(String codigo) {
        return cotacaoRepository.findById(codigo);
    }

    public Cotacao saveCotacao(Cotacao cotacao) {
        cotacao.setDataCriacao(LocalDateTime.now());
        return cotacaoRepository.save(cotacao);
    }

    public void deleteCotacao(Cotacao cotacao) {
        cotacaoRepository.delete(cotacao);
    }

    public Cotacao criaCotacaoDeApi(String origem, String destino) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://economia.awesomeapi.com.br/json/last/" + origem + "-" + destino;

        // Dados da cotacao requisitada
        ResponseEntity<Map<String, DadosCotacao>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, DadosCotacao>>() {});
        Map<String, DadosCotacao> dadosCotacaoMap = response.getBody();

        if (dadosCotacaoMap == null) {
            throw new RuntimeException("Dados de cotação não encontrados para " + origem + " e " + destino);
        }

        DadosCotacao dadosCotacao = dadosCotacaoMap.get(origem + destino);

        // Crie a nova instância da entidade Cotacao
        Cotacao cotacao = new Cotacao();
        String codigo = UUID.randomUUID().toString();
        cotacao.setCodigo(codigo);
        cotacao.setOrigem(origem);
        cotacao.setDestino(destino);
        cotacao.setNome(dadosCotacao.getName());
        cotacao.setDataConsulta(dadosCotacao.getCreate_date());
        cotacao.setCompra(Double.parseDouble(dadosCotacao.getBid()));
        cotacao.setVenda(Double.parseDouble(dadosCotacao.getAsk()));

        // Salve a nova cotação no banco de dados e retorne o objeto salvo
        return saveCotacao(cotacao);
    }
}
