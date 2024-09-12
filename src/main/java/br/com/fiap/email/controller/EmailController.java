package br.com.fiap.email.controller;

import br.com.fiap.email.dto.EmailDto;
import br.com.fiap.email.dto.EmailExibicaoDto;
import br.com.fiap.email.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/email")
    @ResponseStatus(HttpStatus.CREATED)
    public EmailExibicaoDto salvar(@RequestBody @Valid EmailDto emailDto) {
        return emailService.postEmail(emailDto);
    }

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public List<EmailExibicaoDto> litarTodos() {
        return emailService.listEmail();
    }

    @GetMapping("/email/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmailExibicaoDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(emailService.searchEmailbyId(id));
    }


}
