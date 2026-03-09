package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.TimeRequestDTO;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/times")
public class TimeController {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private IntegranteRepository integranteRepository;

    @GetMapping
    public List<Time> listarTodos() {
        return timeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Time> salvar(@RequestBody TimeRequestDTO dto) {
        Time novoTime = new Time();
        novoTime.setData(dto.getData());

        // Busca os integrantes no banco pelos IDs enviados
        List<Integrante> integrantes = integranteRepository.findAllById(dto.getIntegranteIds());

        if (integrantes.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Cria a composição do time
        List<ComposicaoTime> composicoes = integrantes.stream()
                .map(i -> new ComposicaoTime(null, novoTime, i))
                .collect(Collectors.toList());

        novoTime.setComposicoes(composicoes);

        return ResponseEntity.ok(timeRepository.save(novoTime));
    }
}