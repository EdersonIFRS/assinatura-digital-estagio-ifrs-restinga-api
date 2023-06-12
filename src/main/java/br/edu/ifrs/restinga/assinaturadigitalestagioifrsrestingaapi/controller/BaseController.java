package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.ImplClasses.ServidorImplementacao;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.*;
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
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    protected
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    public DocumentoRepository documentoRepository;

    @Autowired
    protected
    EmailValidator emailValidator;

    @Autowired
    public ServidorRepository servidorRepository;

    @Autowired
    public RoleRepository roleRepository;

}
