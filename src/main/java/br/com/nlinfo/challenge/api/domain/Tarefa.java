package br.com.nlinfo.challenge.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Integer prioridade;

    @CreationTimestamp
    private Timestamp dataCadastro;

    private Boolean isFinish;

    public Tarefa(String descriptiontest, int prioridade) {
        this.descricao=descriptiontest;
        this.prioridade=prioridade;
    }
}
