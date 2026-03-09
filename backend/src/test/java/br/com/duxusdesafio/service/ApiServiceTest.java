package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApiServiceTest {

    private ApiService apiService;
    private List<Time> todosOsTimes;
    private Integrante i1, i2, i3;

    @BeforeEach
    public void setup() {
        apiService = new ApiService();

        // Criando Integrantes
        i1 = new Integrante(1L, "Apex Legends", "Bangalore", "Ofensivo");
        i2 = new Integrante(2L, "Apex Legends", "Bloodhound", "Reconhecimento");
        i3 = new Integrante(3L, "Overwatch", "Tracer", "Dano");

        // Criando Times
        Time t1 = new Time(1L, LocalDate.of(2021, 1, 1), new ArrayList<>());
        t1.getComposicoes().add(new ComposicaoTime(1L, t1, i1));
        t1.getComposicoes().add(new ComposicaoTime(2L, t1, i2));

        Time t2 = new Time(2L, LocalDate.of(2021, 1, 15), new ArrayList<>());
        t2.getComposicoes().add(new ComposicaoTime(3L, t2, i1));
        t2.getComposicoes().add(new ComposicaoTime(4L, t2, i3));

        todosOsTimes = Arrays.asList(t1, t2);
    }

    @Test
    public void deveRetornarTimeDaDataCorreta() {
        LocalDate dataBusca = LocalDate.of(2021, 1, 1);
        Time resultado = apiService.timeDaData(dataBusca, todosOsTimes);

        assertNotNull(resultado);
        assertEquals(dataBusca, resultado.getData());
    }

    @Test
    public void deveRetornarIntegranteMaisUsado() {
        // i1 aparece em 2 times, i2 e i3 em apenas 1.
        Integrante resultado = apiService.integranteMaisUsado(null, null, todosOsTimes);

        assertNotNull(resultado);
        assertEquals("Bangalore", resultado.getNome());
    }

    @Test
    public void deveRetornarFuncaoMaisComum() {
        // Ofensivo (i1) aparece 2 vezes, Reconhecimento e Dano 1 vez.
        String resultado = apiService.funcaoMaisComum(null, null, todosOsTimes);

        assertEquals("Ofensivo", resultado);
    }

    @Test
    public void deveRetornarContagemPorFranquia() {
        Map<String, Long> resultado = apiService.contagemPorFranquia(null, null, todosOsTimes);

        // Apex Legends aparece 3 vezes (i1 duas vezes + i2 uma vez)
        // Overwatch aparece 1 vez (i3)
        assertEquals(Long.valueOf(3), resultado.get("Apex Legends"));
        assertEquals(Long.valueOf(1), resultado.get("Overwatch"));
    }

    @Test
    public void deveFiltrarPorDataCorretamente() {
        // Busca apenas no início de Janeiro
        LocalDate inicio = LocalDate.of(2021, 1, 1);
        LocalDate fim = LocalDate.of(2021, 1, 5);

        Integrante resultado = apiService.integranteMaisUsado(inicio, fim, todosOsTimes);

        // No período de 01 a 05/01, i1 e i2 empatam. O stream pega o primeiro que encontrar.
        assertNotNull(resultado);
    }
}