package br.com.nlinfo.challenge.api.repository;


import br.com.nlinfo.challenge.api.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
    @Override
    Optional<Tarefa> findById(Long aLong);
}
