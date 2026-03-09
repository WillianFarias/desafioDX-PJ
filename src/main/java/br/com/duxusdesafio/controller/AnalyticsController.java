package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.ApiService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private ApiService apiService;
    private TimeRepository timeRepository;

    public AnalyticsController(ApiService apiService, TimeRepository timeRepository) {
        this.apiService = apiService;
        this.timeRepository = timeRepository;
    }

    @GetMapping("/time-da-data")
    public ResponseEntity<?> getTimeDaData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        List<Time> todosOsTimes = timeRepository.findAll();
        Time time = apiService.timeDaData(data, todosOsTimes);

        if (time == null) {
            return ResponseEntity.notFound().build();
        }

        // Formata o retorno conforme o exemplo do desafio
        Map<String, Object> response = new HashMap<>();
        response.put("data", time.getData());
        response.put("integrantes", apiService.integrantesDoTimeMaisComum(data, data, todosOsTimes));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/integrante-mais-usado")
    public ResponseEntity<Integrante> getIntegranteMaisUsado(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        List<Time> todosOsTimes = timeRepository.findAll();
        return ResponseEntity.ok(apiService.integranteMaisUsado(inicio, fim, todosOsTimes));
    }

    @GetMapping("/funcao-mais-comum")
    public ResponseEntity<Map<String, String>> getFuncaoMaisComum(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        List<Time> todosOsTimes = timeRepository.findAll();
        String funcao = apiService.funcaoMaisComum(inicio, fim, todosOsTimes);

        Map<String, String> response = new HashMap<>();
        response.put("Função", funcao);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/franquia-mais-famosa")
    public ResponseEntity<Map<String, String>> getFranquiaMaisFamosa(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        List<Time> todosOsTimes = timeRepository.findAll();
        String franquia = apiService.franquiaMaisFamosa(inicio, fim, todosOsTimes);

        Map<String, String> response = new HashMap<>();
        response.put("Franquia", franquia);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/contagem-por-franquia")
    public ResponseEntity<Map<String, Long>> getContagemPorFranquia(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        List<Time> todosOsTimes = timeRepository.findAll();
        return ResponseEntity.ok(apiService.contagemPorFranquia(inicio, fim, todosOsTimes));
    }

    @GetMapping("/contagem-por-funcao")
    public ResponseEntity<Map<String, Long>> getContagemPorFuncao(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        List<Time> todosOsTimes = timeRepository.findAll();
        return ResponseEntity.ok(apiService.contagemPorFuncao(inicio, fim, todosOsTimes));
    }
}