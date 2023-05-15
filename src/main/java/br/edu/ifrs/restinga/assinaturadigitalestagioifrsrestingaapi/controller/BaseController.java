package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.ImplClasses.ServidorImplementacao;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.AlunoRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.ServidorRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.UsuarioRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.infra.security.TokenService;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    protected
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    protected
    EmailValidator emailValidator;

    @Autowired
    public ServidorRepository servidorRepository;

}
