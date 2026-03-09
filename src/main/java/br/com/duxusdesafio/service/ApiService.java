package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados solicitados no desafio!
 */
@Service
public class ApiService {

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        return todosOsTimes.stream()
            .filter(t -> t.getData().equals(data))
            .findFirst()
            .orElse(null);
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        List<Time> filtrados = filtrarPorData(dataInicial, dataFinal, todosOsTimes);

        return filtrados.stream()
            .flatMap(t -> t.getComposicoes().stream())
            .map(ComposicaoTime::getIntegrante)
            .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        List<Time> filtrados = filtrarPorData(dataInicial, dataFinal, todosOsTimes);

        // Agrupa times pela composição de IDs de integrantes (ordenados para garantir igualdade)
        Map<String, Long> contagemComposicao = filtrados.stream()
            .map(this::gerarChaveComposicao)
            .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        String chaveMaisComum = contagemComposicao.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("");

        // Retorna os nomes dos integrantes daquela composição
        return filtrados.stream()
            .filter(t -> gerarChaveComposicao(t).equals(chaveMaisComum))
            .findFirst()
            .map(t -> t.getComposicoes().stream()
                .map(c -> c.getIntegrante().getNome())
                .collect(Collectors.toList()))
            .orElse(Collections.emptyList());
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!
        return null;
    }


    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    // Métodos auxiliares privados
    private List<Time> filtrarPorData(LocalDate inicio, LocalDate fim, List<Time> times) {
        return times.stream()
            .filter(t -> (inicio == null || !t.getData().isBefore(inicio)) &&
                    (fim == null || !t.getData().isAfter(fim)))
            .collect(Collectors.toList());
    }

    private String gerarChaveComposicao(Time t) {
        return t.getComposicoes().stream()
            .map(c -> c.getIntegrante().getId().toString())
            .sorted()
            .collect(Collectors.joining("-"));
    }
}
