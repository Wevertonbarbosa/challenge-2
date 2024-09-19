package br.com.fiap.email.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class EmailCCId implements Serializable {


    @Column(name = "id_eml")
    private int emailId;

    @Column(name = "cc_eml")
    private String email;
}
