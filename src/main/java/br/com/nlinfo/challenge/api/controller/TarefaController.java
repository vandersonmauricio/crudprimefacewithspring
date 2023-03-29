package br.com.nlinfo.challenge.api.controller;

import br.com.nlinfo.challenge.api.domain.Tarefa;
import br.com.nlinfo.challenge.api.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService service;

    @PostMapping
    public void incluir(@RequestBody Tarefa tarefa) {
        service.save(tarefa);
    }

    @GetMapping
    public List<Tarefa> listar() {
        return service.listar();
    }
    @PutMapping
    public void alterar(@RequestBody Tarefa tarefa){
        service.alterar(tarefa.getId(), tarefa);

    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
