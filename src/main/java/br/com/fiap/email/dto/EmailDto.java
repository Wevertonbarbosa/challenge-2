package br.com.fiap.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailDto(
        Long id,
        @NotBlank(message = "Campo remetente é obrigatório!")
        @Email(message = "email inválido")
        String remetente,
        @Size(min = 2)
        @Email(message = "email inválido")
        String copia,
        @NotBlank(message = "Corpo do email é obrigatório!")
        String body_email,
        @NotBlank(message = "Assunto é obrigatório!")
        String assunto,
        @NotBlank(message = "Campo destinatario é obrigatório!")
        @Email(message = "email inválido")
        String destinatario,
        @Size(min = 2)
        String anexo,
        @NotBlank(message = "Campo categoria é obrigatório!")
        String categoria,
        @NotBlank(message = "Campo senha é obrigatório!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres!")
        String senha
) {
}
