package br.iesb.cco.apipoo.controller;

import br.iesb.cco.apipoo.model.UserEntity;
import br.iesb.cco.apipoo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity user) {
        String token = service.login(user);

        if (token == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Authorization", token);

        return ResponseEntity.ok().headers(responseHeader).build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUsers() {
        List<UserEntity> list = service.getUsers();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserEntity user) {

        int result = service.signup(user);

        if (result == 1) {
            return ResponseEntity.badRequest().body("E-mail de usuario invalido!");
        } else if (result == 2) {
            return ResponseEntity.badRequest().body("Senha deve ter mais que 6 caracters.");
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/forgotpass")
    public ResponseEntity<String> forgotPassword(@RequestBody UserEntity user) {

        int result = service.forgotPassword(user);

        if (result == 1) {
            return ResponseEntity.badRequest().body("E-mail de usuario invalido!");
        }
        else if (result == 2) {
            return ResponseEntity.badRequest().body("Falha ao enviar email!");
        }

        return ResponseEntity.ok().build();
    }

}
