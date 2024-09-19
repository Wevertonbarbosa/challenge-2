package br.com.fiap.email.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AddPreferenceInPutDto(
        String email,
        String theme,
        @JsonProperty("font_size")
        int fontSize
) {
}
