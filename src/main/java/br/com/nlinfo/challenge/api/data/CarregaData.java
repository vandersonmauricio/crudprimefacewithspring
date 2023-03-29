package br.com.nlinfo.challenge.api.data;

import br.com.nlinfo.challenge.api.domain.Tarefa;
import br.com.nlinfo.challenge.api.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.Instant;

@Component
public class CarregaData {

    @Value("${habilitar.carregar.massa.dados}")
    private boolean podeCarregarDados;

    @Autowired
    private TarefaRepository tarefaRepository;

    @PostConstruct
    public void loadData(){
        if (podeCarregarDados){
            for (int x=0; x<50; x++){
                tarefaRepository.save(new Tarefa((long)x,"description test",x,Timestamp.from(Instant.now()),false));
            }
        }

    }
}
