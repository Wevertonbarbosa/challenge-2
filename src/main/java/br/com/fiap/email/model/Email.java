package br.com.fiap.email.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "email")
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
    @Column(name = "snd")
    private String sender;
    @Column(name = "bdy")
    private String body;
    @Column(name = "sbj")
    private String subject;
    @Column(name = "rcp")
    private String recipient;
    @Column(name = "atc")
    private String attachment;
    @Column(name = "ctg")
    private String category;
    @Column(name = "dat")
    private LocalDateTime date;
    @Column(name = "red")
    private boolean read;
    @Column(name = "ibx")
    private boolean inbox;

}
