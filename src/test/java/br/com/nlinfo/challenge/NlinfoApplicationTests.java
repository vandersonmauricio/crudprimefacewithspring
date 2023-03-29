package br.com.nlinfo.challenge;

import br.com.nlinfo.challenge.api.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NlinfoApplicationTests {

    @Autowired
    private TarefaRepository tarefaRepository;
    @Test
    void contextLoads() {
    }
}
