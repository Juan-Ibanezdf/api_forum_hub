package br.com.alura.forum.controller;

import br.com.alura.forum.domain.usuario.DadosRegistroUsuario;
import br.com.alura.forum.domain.usuario.Usuario;
import br.com.alura.forum.domain.usuario.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DadosRegistroUsuario dados) {
        if (usuarioRepository.findByEmail(dados.email()).isPresent()) {
            return ResponseEntity.badRequest().body("O email já está em uso.");
        }


        if (usuarioRepository.findByNome(dados.nome()).isPresent()) {
             return ResponseEntity.badRequest().body("O nome de usuário já está em uso.");
         }

        var encoder = new BCryptPasswordEncoder();
        var senhaHash = encoder.encode(dados.senha());

        var usuario = new Usuario(
                0L, // O ID será gerado automaticamente pelo banco
                dados.nome(),
                dados.email(),
                senhaHash,
                new HashSet<>()
        );

        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

}
