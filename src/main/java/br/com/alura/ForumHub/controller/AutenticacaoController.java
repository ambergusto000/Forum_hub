package br.com.alura.ForumHub.controller;


import br.com.alura.ForumHub.dto.AutenticacaoRequestDTO;
import br.com.alura.ForumHub.dto.DadosTokenJWT;
import br.com.alura.ForumHub.entity.Usuario;
import br.com.alura.ForumHub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid AutenticacaoRequestDTO autenticacaoRequestDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(autenticacaoRequestDTO.username(), autenticacaoRequestDTO.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}