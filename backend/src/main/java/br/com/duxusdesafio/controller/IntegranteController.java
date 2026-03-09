package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/integrantes")
public class IntegranteController {

    private IntegranteRepository integranteRepository;

    public IntegranteController(IntegranteRepository integranteRepository) {
        this.integranteRepository = integranteRepository;
    }

    @GetMapping
    public List<Integrante> listarTodos() {
        return integranteRepository.findAll();
    }

    @PostMapping
    public Integrante salvar(@RequestBody Integrante integrante) {
        return integranteRepository.save(integrante);
    }
}