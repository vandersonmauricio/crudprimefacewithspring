package br.com.nlinfo.challenge.api.service;

import br.com.nlinfo.challenge.api.domain.Tarefa;
import br.com.nlinfo.challenge.api.exception.BusinessException;
import br.com.nlinfo.challenge.api.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;

    public Tarefa save(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public List<Tarefa> listar() {
        return repository.findAll();
    }

    public void alterar(Long id, Tarefa tarefa) {
        Optional<Tarefa> optionalTarefa = repository.findById(id);
        if (optionalTarefa.isEmpty()) {
            throw new BusinessException("tarefa não foi encontrada");
        }

        repository.save(tarefa);
    }

    public void deletar(Long id) {
        Optional<Tarefa> tarefaOptional = repository.findById(id);
        if (tarefaOptional.isEmpty()) {
            throw new BusinessException("não existe tarefa com esse id");
        }

        repository.deleteById(id);
    }
}
