package br.unibh.sdm.cambiu.backendmoeda.persistencia;

import br.unibh.sdm.cambiu.backendmoeda.entidades.Moeda;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface MoedaRepository extends CrudRepository<Moeda, String> {
}
