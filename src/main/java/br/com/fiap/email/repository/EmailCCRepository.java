package br.com.fiap.email.repository;

import br.com.fiap.email.model.EmailCC;
import br.com.fiap.email.model.EmailCCId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmailCCRepository extends JpaRepository<EmailCC, EmailCCId> {

     Optional<List<EmailCC>> findByIdEmailId(long emailId);
}
