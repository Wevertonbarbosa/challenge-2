package br.com.fiap.email.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_email")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class Email {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TBL_EMAIL_SEQ"
    )
    @SequenceGenerator(
            name = "TBL_EMAIL_SEQ",
            sequenceName = "TBL_EMAIL_SEQ",
            allocationSize = 1
    )
    private Long id;

    private String remetente;
    private String copia;

    @Column(name = "CORPO_EMAIL")
    private String body_email;

    private String assunto;
    private String destinatario;
    private String anexo;
    private String categoria;
    private String senha;



}
