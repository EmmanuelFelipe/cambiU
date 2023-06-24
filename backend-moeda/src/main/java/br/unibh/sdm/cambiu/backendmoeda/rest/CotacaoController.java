package br.unibh.sdm.cambiu.backendmoeda.rest;

import br.unibh.sdm.cambiu.backendmoeda.entidades.Cotacao;
import br.unibh.sdm.cambiu.backendmoeda.negocio.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  io.swagger.v3.oas.annotations.Operation;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value="/cotacao")
public class CotacaoController {

    private final CotacaoService cotacaoService;

    @Autowired
    public CotacaoController(CotacaoService cotacaoService) {
        this.cotacaoService = cotacaoService;
    }

    @GetMapping(value = "/lista")
    public ResponseEntity<Iterable<Cotacao>> getCotacoes() {
        return ResponseEntity.ok(cotacaoService.getCotacoes());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cotacao> getCotacaoByCodigo(@PathVariable String codigo) {
        Optional<Cotacao> cotacaoOptional = cotacaoService.getCotacaoByCodigo(codigo);
        if (!cotacaoOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cotacaoOptional.get());
    }

    @Operation(summary = "Cria uma cotação com a origem e o destino origem-destino", description = "Exemplo de request: /cotacao/USD-BRL")
    @PostMapping("/{origemDestino}")
    public ResponseEntity<Cotacao> createCotacao(@PathVariable String origemDestino) {
        String[] parts = origemDestino.split("-");
        if(parts.length != 2) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cotacaoService.criaCotacaoDeApi(parts[0], parts[1]), HttpStatus.CREATED);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteCotacao(@PathVariable String codigo) {
        Optional<Cotacao> cotacaoOptional = cotacaoService.getCotacaoByCodigo(codigo);
        if (!cotacaoOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cotacaoService.deleteCotacao(cotacaoOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
