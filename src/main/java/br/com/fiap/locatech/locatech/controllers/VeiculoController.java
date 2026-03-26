package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.services.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veículo", description = "Controller para CRUD de veículos")
public class VeiculoController {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Operation(
            summary = "Listar veículos",
            description = "Retorna uma lista paginada de veículos cadastrados no sistema"
    )
    @ApiResponse(responseCode = "200", description = "Veículos encontrados com sucesso")
    @GetMapping
    public ResponseEntity<List<Veiculo>> findAllVeiculos(
            @Parameter(description = "Número da página", example = "0")
            @RequestParam("page") int page,

            @Parameter(description = "Quantidade de registros por página", example = "100")
            @RequestParam("size") int size
    ) {
        logger.info("GET -> /veiculos - page: {}, size: {}", page, size);
        var veiculos = this.veiculoService.findAllVeiculos(page, size);
        return ResponseEntity.ok(veiculos);
    }

    @Operation(
            summary = "Listar veículo",
            description = "Retona um veículo a partir do id"
    )
    @ApiResponse(responseCode = "200", description = "Veículo encontrado com sucesso")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> findVeiculo(
            @Parameter(description = "id do veículo", example = "123")
            @PathVariable Long id
    ) {
        logger.info("GET -> /veiculos/"+ id);
        var veiculo = this.veiculoService.findVeiculoById(id);
        return ResponseEntity.ok(veiculo);
    }

    @Operation(
            summary = "Cadastrar veículo",
            description = "Insere novo veículo ao sistema. Podem haver duplicações"
    )
    @PostMapping
    public ResponseEntity<Void> saveVeiculo(
            @Parameter(description = "Objeto veículo completo")
            @RequestBody Veiculo veiculo
    ) {
        logger.info("POST -> /veiculos -> {}", veiculo.toString());
        this.veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(201).build();
    }

    @Operation(
            summary = "Atualizar veículo",
            description = "Atualiza veículo a partir do id e dados informados"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(
            @Parameter(description = "id do veículo", example = "123")
            @PathVariable Long id,

            @Parameter(description = "Objeto veículo")
            @RequestBody Veiculo veiculo
    ) {
        logger.info("PUT -> /veiculos -> {}", veiculo.toString());
        this.veiculoService.updateVeiculo(veiculo, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @Operation(
            summary = "Deletar veículo",
            description = "Deleta um veículo a partir do id"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "id do veículo", example = "123")
            @PathVariable("id") Long id
    ) {
        logger.info("DELETE -> /veiculos -> id: {}", id);
        this.veiculoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
