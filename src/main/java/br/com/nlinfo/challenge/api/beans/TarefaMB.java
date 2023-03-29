package br.com.nlinfo.challenge.api.beans;

import br.com.nlinfo.challenge.api.controller.TarefaController;
import br.com.nlinfo.challenge.api.domain.Tarefa;
import br.com.nlinfo.challenge.api.service.TarefaService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Named(value = "tarefaMB")
@ViewScoped
public class TarefaMB {


    @Getter
    @Setter
    private List<Tarefa> tarefas = new ArrayList<>();

    @Getter
    @Setter
    private Tarefa tarefa = new Tarefa();




    @Autowired
    private TarefaController tarefaController;

    public List<Tarefa> listarTodos() {
        tarefas = tarefaController.listar();
        return tarefas;
    }

    public void deletarById(Long id) {
        tarefaController.deletar(id);
    }


    public void salvarTarefa() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String descricao = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{tarefaMB.tarefa.descricao}", String.class);
        Integer prioridade = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{tarefaMB.tarefa.prioridade}", Integer.class);

        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setDescricao(descricao);
        novaTarefa.setPrioridade(prioridade);
        novaTarefa.setIsFinish(false);
        tarefaController.incluir(novaTarefa);
    }


    public Integer getTamanhoDaLista() {
        return tarefas.size();
    }

    public void setTamanhoDaLista(Integer size) {
        // Método criado para ser utilizado pelo primefaces
    }
}
