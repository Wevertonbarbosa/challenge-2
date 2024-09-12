package br.com.fiap.email.dto;

import br.com.fiap.email.model.Email;

public record EmailExibicaoDto(
        Long id,
        String remetente,
        String copia,
        String body_email,
        String assunto,
        String destinatario,
        String anexo,
        String categoria
) {

    public EmailExibicaoDto(Email email) {
        this(
                email.getId(),
                email.getRemetente(),
                email.getCopia(),
                email.getBody_email(),
                email.getAssunto(),
                email.getDestinatario(),
                email.getAnexo(),
                email.getCategoria()
        );
    }


}
