package br.com.fiap.email.repository;

import br.com.fiap.email.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, String> {
}
