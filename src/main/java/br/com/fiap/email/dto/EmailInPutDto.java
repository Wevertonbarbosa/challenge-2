package br.com.fiap.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record EmailInPutDto(

        @NotBlank(message = "Campo remetente é obrigatório!")
        @Email(message = "email inválido")
        String sender,
        @NotBlank(message = "Corpo do email é obrigatório!")
        String body,
        @NotBlank(message = "Assunto é obrigatório!")
        String subject,
        @NotBlank(message = "Campo destinatario é obrigatório!")
        @Email(message = "email inválido")
        String recipient
) {
}
