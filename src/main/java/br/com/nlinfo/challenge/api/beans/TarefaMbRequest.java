package br.com.nlinfo.challenge.api.beans;

import br.com.nlinfo.challenge.api.controller.TarefaController;
import br.com.nlinfo.challenge.api.domain.Tarefa;
import br.com.nlinfo.challenge.api.repository.TarefaRepository;
import br.com.nlinfo.challenge.api.service.TarefaService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named("tarefaMbRequest")
@RequestScoped
public class TarefaMbRequest implements Serializable {

    @Getter
    @Setter
    private Tarefa tarefa = new Tarefa();
    @Autowired
    TarefaController controller;

    public void salvarTarefa(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String descricao = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{tarefaMbRequest.tarefa.descricao}", String.class);
        Integer prioridade = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{tarefaMbRequest.tarefa.prioridade}", Integer.class);
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setDescricao(descricao);
        novaTarefa.setPrioridade(prioridade);
        novaTarefa.setIsFinish(false);
        controller.incluir(novaTarefa);
    }

}