package br.com.fiap.email.controller;

import br.com.fiap.email.dto.DetailEmailOutPutDto;
import br.com.fiap.email.dto.EmailInPutDto;
import br.com.fiap.email.dto.EmailOutPutDto;
import br.com.fiap.email.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> sendMail(@RequestBody @Valid final EmailInPutDto emailInPutDto) {
        return emailService.postEmail(emailInPutDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmailOutPutDto> listAll() {
        return emailService.listEmail();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DetailEmailOutPutDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(emailService.findEmailById(id));
    }

    @GetMapping("/date")
    @ResponseStatus(HttpStatus.OK)
    public List<EmailOutPutDto> listAllByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return emailService.listEmailByDate(date);
    }
}
