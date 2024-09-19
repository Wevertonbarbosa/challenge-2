package br.com.fiap.email.dto;

import br.com.fiap.email.model.Email;

import java.time.LocalDateTime;

public record EmailOutPutDto(
        Long id,
        String subject,
        LocalDateTime date,
        String category,
        Boolean read
) {

    public EmailOutPutDto(Email email) {
        this(
                email.getId(),
                email.getSubject(),
                email.getDate(),
                email.getCategory(),
                email.isRead()
        );
    }


}
