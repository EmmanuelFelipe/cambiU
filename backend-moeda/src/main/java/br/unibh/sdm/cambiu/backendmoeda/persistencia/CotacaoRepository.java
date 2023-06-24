package br.unibh.sdm.cambiu.backendmoeda.persistencia;

import br.unibh.sdm.cambiu.backendmoeda.entidades.Cotacao;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface CotacaoRepository extends CrudRepository<Cotacao, String> {
}
