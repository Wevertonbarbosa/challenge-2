package br.com.fiap.email.dto;

import br.com.fiap.email.model.Preference;

public record GetPreferenceOutPutDto(
        String theme,
        int color
) {
    public GetPreferenceOutPutDto(Preference preferece) {
        this(preferece.getTheme(), preferece.getFontSize());
    }
}
