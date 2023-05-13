package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator {
    private static final String RESTINGA_DOMAIN_PATTERN = "@restinga.ifrs.edu.br$";
    private static final Pattern pattern = Pattern.compile(RESTINGA_DOMAIN_PATTERN);

    public boolean validaEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }


}