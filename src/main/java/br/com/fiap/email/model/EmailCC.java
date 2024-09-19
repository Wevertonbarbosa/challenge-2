package br.com.fiap.email.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cc_email")
@ToString
@EqualsAndHashCode(of = "id")
@Entity
public class EmailCC {

@EmbeddedId
private EmailCCId id;
}
