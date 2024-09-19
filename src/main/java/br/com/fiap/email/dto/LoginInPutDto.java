package br.com.fiap.email.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginInPutDto(

        @NotBlank(message = "Login cannot be blank")
        @NotNull(message = "Login cannot be null")
        String login,
        @NotBlank(message = "Login cannot be blank")
        @NotNull(message = "Login cannot be null")
        @Min(5)
        String password

) {
}