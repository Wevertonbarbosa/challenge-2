package br.com.fiap.email.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "spam_words")
@AllArgsConstructor
@EqualsAndHashCode(of = "word")
@ToString
@Entity
public class BlackListSpamWords {
    @Id
    @Column(name = "wrd")
    private String word;
    @Column(name = "trt_lvl")
    private int threatLevel;
}
