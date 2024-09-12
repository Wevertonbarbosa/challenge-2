package br.com.fiap.email.repository;

import br.com.fiap.email.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface EmailRepository extends JpaRepository<Email, Long> {

//    UserDetails findByEmail(String email);

}
