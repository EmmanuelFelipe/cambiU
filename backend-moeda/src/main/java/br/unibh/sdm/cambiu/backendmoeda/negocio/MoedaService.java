package br.unibh.sdm.cambiu.backendmoeda.negocio;

import br.unibh.sdm.cambiu.backendmoeda.entidades.Moeda;
import br.unibh.sdm.cambiu.backendmoeda.persistencia.MoedaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoedaService {

    private final MoedaRepository moedaRepository;

    public MoedaService(MoedaRepository moedaRepository) {
        this.moedaRepository = moedaRepository;
    }

    public Iterable<Moeda> getMoedas() {
        return moedaRepository.findAll();
    }

    public Optional<Moeda> getMoedaByCodigo(String codigo) {
        return moedaRepository.findById(codigo);
    }

    public Moeda saveMoeda(Moeda moeda) {
        return moedaRepository.save(moeda);
    }

    public void deleteMoeda(Moeda moeda) {
        moedaRepository.delete(moeda);
    }
}
