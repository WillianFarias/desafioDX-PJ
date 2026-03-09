package br.com.duxusdesafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "composicao_time")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ComposicaoTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_time")
    @JsonIgnore // Evita recursão infinita no JSON
    private Time time;

    @ManyToOne
    @JoinColumn(name = "id_integrante")
    private Integrante integrante;
}