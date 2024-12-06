package br.com.alura.forum.controller;

import br.com.alura.forum.domain.topico.*;
import br.com.alura.forum.domain.curso.CursoRepository;
import br.com.alura.forum.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/cursos/{idCurso}/topicos")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de tópicos retornada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Proibido"),
        @ApiResponse(responseCode = "404", description = "Não encontrado")
})
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> cadastrar(
            @PathVariable Long idCurso,
            @RequestBody @Valid DadosCadastroTopico dados,
            UriComponentsBuilder uriBuilder,
            Principal principal) {

        var autor = usuarioRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        var curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        var topico = new Topico(dados, autor, curso);
        repository.save(topico);

        var uri = uriBuilder.path("/cursos/{idCurso}/topicos/{idTopico}")
                .buildAndExpand(idCurso, topico.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        var topicos = repository.findAll(paginacao);
        var dadosListagem = topicos.map(DadosListagemTopico::new);
        return ResponseEntity.ok(dadosListagem);
    }

    @GetMapping("/{idTopico}")
    public ResponseEntity<DadosDetalhamentoTopico> listarPorId(
            @PathVariable Long idCurso,
            @PathVariable Long idTopico) {

        cursoRepository.findById(idCurso)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        var topico = repository.findById(idTopico)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico não encontrado"));

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{idTopico}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> atualizar(
            @PathVariable Long idTopico,
            @RequestBody @Valid DadosAtualizacaoTopico dados,
            Principal principal) {

        var topico = repository.findById(idTopico)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico não encontrado"));

        if (!topico.getAutor().getEmail().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        topico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{idTopico}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long idTopico, Principal principal) {
        var topico = repository.findById(idTopico)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico não encontrado"));

        if (!topico.getAutor().getEmail().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        repository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}
