package br.com.fiap.email.config.security;

import br.com.fiap.email.exception.ObjectNotFoundException;
import br.com.fiap.email.model.Person;
import br.com.fiap.email.model.User;
import br.com.fiap.email.repository.PersonRepository;
import br.com.fiap.email.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class VerifyToken extends OncePerRequestFilter {
    @Autowired
    private TokenService service;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (!Objects.isNull(authHeader)) {
            String token = authHeader.replace("Bearer", "").trim();
            String login = service.verifyToken(token);
            UsernamePasswordAuthenticationToken authentication = getUsernamePasswordAuthenticationToken(login);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String login) {
        Person person = personRepository.findById(login).
                orElseThrow(() -> new ObjectNotFoundException("Person Not Found"));
        User user = userRepository.findByPerson(person).
                orElseThrow(() -> new ObjectNotFoundException("User Not Found"));
        return new UsernamePasswordAuthenticationToken(user,
                null, user.getAuthorities());
    }
}