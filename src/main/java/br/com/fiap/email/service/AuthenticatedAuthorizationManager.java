package br.com.fiap.email.service;

import br.com.fiap.email.model.Person;
import br.com.fiap.email.repository.PersonRepository;
import br.com.fiap.email.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticatedAuthorizationManager implements UserDetailsService {

    PersonRepository personRepository;
    UserRepository userRepository;


    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        Person person = personRepository.findById(username).orElseThrow(() ->
                new UsernameNotFoundException("Person Not Found"));
        return userRepository.findByPerson(person).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
