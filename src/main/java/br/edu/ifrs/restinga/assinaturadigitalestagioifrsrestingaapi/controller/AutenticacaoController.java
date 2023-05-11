package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosAutenticacao;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.infra.security.DadoTokenJWT;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.infra.security.TokenService;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Usuario;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var authenticacao = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authenticacao.getPrincipal());

        return ResponseEntity.ok(new DadoTokenJWT(tokenJWT));
    }

}
