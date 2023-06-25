package br.unibh.sdm.cambiu.backendcliente.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.cambiu.backendcliente.entidades.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);

    List<Cliente> findByCpf(String cpf);

    Cliente findById(long id);

}