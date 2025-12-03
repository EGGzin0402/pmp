package com.fatec.web.controller;

import com.fatec.entity.JwtToken;
import com.fatec.event.ForgetEventProducer;
import com.fatec.security.JwtUserDetailsService;
import com.fatec.web.dto.LoginDto;
import com.fatec.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    
    Logger log = LoggerFactory.getLogger(AuthController.class);

    private final JwtUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final ForgetEventProducer forgetEventProducer;

    public AuthController(JwtUserDetailsService userDetailsService, AuthenticationManager authenticationManager, ForgetEventProducer forgetEventProducer) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.forgetEventProducer = forgetEventProducer;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDto user, HttpServletRequest request) {
        try{
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = userDetailsService.getTokenAuthenticated(user.getUsername());
            return ResponseEntity.ok(token);
        } catch (AuthenticationException ex){
            log.info("Falha na autenticação do usuário {}: {}", user.getUsername(), ex.getMessage());
        }
        return ResponseEntity.badRequest().body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais inválidas"));
    }

    @GetMapping("/forget/{username}")
    public ResponseEntity<String> forgetPassword(@PathVariable String username) {
        try {
            forgetEventProducer.send(username);
        } catch (Exception e) {
            log.info("Erro ao enviar evento de esquecimento de senha para o usuário {}: {}", username, e.getMessage());
            Thread.currentThread().interrupt();
        }
        return ResponseEntity.ok("Enviamos a senha associada ao username " + username + " para o email cadastrado. (Caso exista)");
    }

}
