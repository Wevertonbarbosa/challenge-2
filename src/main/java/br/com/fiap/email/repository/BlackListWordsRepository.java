package br.com.fiap.email.repository;

import br.com.fiap.email.model.BlackListSpamWords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListWordsRepository extends JpaRepository<BlackListSpamWords, String> {
}
