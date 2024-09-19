package br.com.fiap.email.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "preference")
@AllArgsConstructor
@EqualsAndHashCode(of = "email")
@ToString
@Entity
public class Preference {
    @Id
    private String email;
    @Column(name = "thm")
    private String theme;
    @Column(name = "fnt_siz")
    private int fontSize;
}
