package br.com.fiap.email.repository;

import br.com.fiap.email.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query("SELECT e FROM Email e WHERE e.date BETWEEN :startOfDay AND :endOfDay")
    Optional<List<Email>> findAllByDateRange(LocalDateTime startOfDay, LocalDateTime endOfDay);


}
