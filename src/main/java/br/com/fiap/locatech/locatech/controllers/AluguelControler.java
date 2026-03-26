package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.dtos.AluguelRequestDTO;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.services.AluguelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alugueis")
@Tag(name = "Alugel", description = "Controller para CRUD de aluguéis")
public class AluguelControler {

    private static final Logger logger = LoggerFactory.getLogger(AluguelControler.class);

    private final AluguelService aluguelService;

    public AluguelControler(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAlugueis (
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("/alugueis");
        var alugueis = this.aluguelService.findAllAlugueis(page, size);
        return ResponseEntity.ok(alugueis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findPessoa (
            @PathVariable("id") Long id
    ) {
        logger.info("/alugueis");
        var aluguel = this.aluguelService.findAluguelById(id);
        return ResponseEntity.ok(aluguel);
    }

    @PostMapping
    public ResponseEntity<Aluguel> saveAluguel(
            @Valid @RequestBody AluguelRequestDTO aluguel
    ) {
        logger.info("POST => /alugueis");
        this.aluguelService.saveAluguel(aluguel);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel (
            @PathVariable Long id,
            @RequestBody Aluguel aluguel
    ) {
        logger.info("PUT => /alugueis");
        this.aluguelService.updateAluguel(aluguel, id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (
            @PathVariable("id") Long id
    ) {
        logger.info("Delete => /alugueis");
        this.aluguelService.delete(id);
        return ResponseEntity.status(204).build();
    }
}
