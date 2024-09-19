package br.com.fiap.email.repository;

import br.com.fiap.email.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}
