package br.unibh.sdm.cambiu.backendmoeda.persistencia;

import br.unibh.sdm.cambiu.backendmoeda.entidades.Moeda;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface MoedaRepository extends CrudRepository<Moeda, String> {
    List<Moeda> findByNome(String nome);
    List<Moeda> findByCodigo(String codigo);
}
