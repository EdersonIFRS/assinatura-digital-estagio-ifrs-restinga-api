package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.infra.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class TratadorDeErros {

    public static ResponseEntity tratarErro400(HttpStatus badRequest) {
        return ResponseEntity.status(badRequest)
                .body("O email informado não está no padrão correto");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public static ResponseEntity tratarErro403(){
        return ResponseEntity.status(403)
                .body("Não Autorizado");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErrorValidacao::new).toList());
    }

    @ExceptionHandler(HttpClientErrorException.Conflict.class)
    public static ResponseEntity tratarErro409(String conflito){
        if(conflito.equals("email")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O email informado já está cadastrado");
        }
        else if(conflito.equals("curso")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já possui um servidor para esse curso!!!");
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Houve algum conflito no banco de dados, tente novamente.");
        }
    }



    private record DadosErrorValidacao(String campo,String mensagem){
         public DadosErrorValidacao(FieldError erro){
             this(erro.getField(), erro.getDefaultMessage());
         }

    }
}
