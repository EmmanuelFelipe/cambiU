package br.unibh.sdm.cambiu.backendmoeda.persistencia;

import br.unibh.sdm.cambiu.backendmoeda.entidades.Cotacao;
import br.unibh.sdm.cambiu.backendmoeda.entidades.Moeda;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface CotacaoRepository extends CrudRepository<Cotacao, String> {
    List<Cotacao> findByNome(String nome);
    List<Cotacao> findByCodigo(String codigo);
}
