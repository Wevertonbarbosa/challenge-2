package br.com.fiap.email.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@ToString
@EqualsAndHashCode(of = "id")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", allocationSize = 1,initialValue = 30)
    @Column(name = "id_use")
    private Long id;

    @Column(name = "log")
    private String login;

    @Column(name = "pwd")
    @Getter(onMethod = @__(@Override))
    private String password;

    @Column(name = "act")
    @Getter(onMethod = @__(@Override))
    private boolean enabled;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Person.class, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "id_per")
    @ToString.Exclude
    private Person person;

    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }
}
