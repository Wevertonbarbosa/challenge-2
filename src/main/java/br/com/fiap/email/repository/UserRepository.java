package br.com.fiap.email.repository;

import br.com.fiap.email.model.Person;
import br.com.fiap.email.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPerson(Person person);
}
