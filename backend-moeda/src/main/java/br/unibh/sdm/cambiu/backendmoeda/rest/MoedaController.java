package br.unibh.sdm.cambiu.backendmoeda.rest;

import br.unibh.sdm.cambiu.backendmoeda.entidades.Moeda;
import br.unibh.sdm.cambiu.backendmoeda.negocio.MoedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value="/moeda")
public class MoedaController {

    private final MoedaService moedaService;

    @Autowired
    public MoedaController(MoedaService moedaService) {
        this.moedaService = moedaService;
    }

    @GetMapping("/lista")
    public List<Moeda> getMoedas() {
        Iterable<Moeda> moedas = moedaService.getMoedas();
        List<Moeda> result = new ArrayList<>();
        moedas.forEach(result::add);
        return result;
    }

    @GetMapping(value="/{codigo}")
    public Moeda getMoeda(@PathVariable String codigo) {
        Optional<Moeda> moeda = moedaService.getMoedaByCodigo(codigo);
        return moeda.orElseThrow(() -> new RuntimeException("Moeda não encontrada"));
    }

    @PostMapping(value="")
    public Moeda createMoeda(@RequestBody Moeda moeda) {
        moeda.setCodigo(UUID.randomUUID().toString());
        return moedaService.saveMoeda(moeda);
    }

    @PutMapping(value="/{codigo}")
    public Moeda updateMoeda(@RequestBody Moeda moeda, @PathVariable String codigo) {
        return moedaService.saveMoeda(moeda);
    }

    @DeleteMapping(value="/{codigo}")
    public void deleteMoeda(@PathVariable String codigo) {
        Optional<Moeda> moeda = moedaService.getMoedaByCodigo(codigo);
        moeda.ifPresent(m -> moedaService.deleteMoeda(m));
    }
}
