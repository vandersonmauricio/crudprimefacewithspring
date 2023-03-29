package br.com.nlinfo.challenge;

import br.com.nlinfo.challenge.api.domain.Tarefa;
import br.com.nlinfo.challenge.api.repository.TarefaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NlinfoApplicationTests {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    final Tarefa tarefa = new Tarefa("descriptiontest", 1);
    final Tarefa tarefaParaAlterar = new Tarefa("Jose da Silva Santos", 1);

    @Test
    void crudTarefa() throws Exception{
        tarefaRepository.deleteAll();
        incluirTarefa(tarefa);
        alterarTarefa(tarefaParaAlterar);

        Tarefa tarefaConsultada= consultarTarefa(tarefaParaAlterar.getId());

        assertEquals(tarefaParaAlterar,tarefaConsultada);
        deletarTarefa(tarefaConsultada.getId());

        var listTarefas=listarTarefas();
        assertEquals(0,listTarefas.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void dadosInvalidos(String conteudo) throws Exception {
        tarefaRepository.deleteAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpRequest = new HttpEntity<>(objectMapper.writeValueAsString(conteudo), httpHeaders);
        var responseCode = restTemplate.postForEntity(getURLDoServico(), httpRequest, Tarefa.class).getStatusCode();
        assertEquals( HttpStatus.BAD_REQUEST , responseCode);
    }


    private Tarefa consultarTarefa(long id) {
        return restTemplate.getForObject(getURLDoServico() + id, Tarefa.class);
    }


    private void deletarTarefa(Long id) {
        var responseCode = restTemplate.exchange(getURLDoServico() + id,
                HttpMethod.DELETE,
                new HttpEntity<>(new HttpHeaders()),
                String.class).getStatusCode();
        assertEquals(HttpStatus.OK, responseCode);
    }

    private void alterarTarefa(Tarefa tarefaParaAlterar) {

        var responseCode = restTemplate.exchange(getURLDoServico(), HttpMethod.PUT,
                new HttpEntity<>(tarefaParaAlterar),
                String.class).getStatusCode();
        assertEquals(HttpStatus.OK, responseCode);
    }

    private void incluirTarefa(final Tarefa tarefa) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpRequest = new HttpEntity<>(objectMapper.writeValueAsString(tarefa), httpHeaders);
        var responseCode = restTemplate.postForEntity(getURLDoServico(), httpRequest, Tarefa.class).getStatusCode();
        assertEquals(HttpStatus.OK, responseCode);
    }

    private List<Tarefa> listarTarefas() {
        return restTemplate.exchange(getURLDoServico(),
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                List.class).getBody();
    }

    private String getURLDoServico() {
        return "http://localhost:" + port + "/tarefas/";
    }
}
