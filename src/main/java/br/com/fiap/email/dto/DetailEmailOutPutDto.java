package br.com.fiap.email.dto;

import br.com.fiap.email.model.Email;

import java.time.LocalDateTime;

public record DetailEmailOutPutDto(
        String subject,
        LocalDateTime date,
        String body,
        String sender
) {
    public DetailEmailOutPutDto(Email email) {
       this(
          email.getSubject(),
          email.getDate(),
          email.getBody(),
          email.getSender()
       );
    }
}
