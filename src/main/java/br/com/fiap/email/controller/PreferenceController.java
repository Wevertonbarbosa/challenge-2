package br.com.fiap.email.controller;

import br.com.fiap.email.dto.AddPreferenceInPutDto;
import br.com.fiap.email.dto.GetPreferenceOutPutDto;
import br.com.fiap.email.service.PreferenceService;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("preference")
public class PreferenceController {

    private final PreferenceService service;

    public PreferenceController(PreferenceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addPreference(@RequestBody @Valid @NonNull final AddPreferenceInPutDto dto) {
        service.add(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPreferenceOutPutDto> getPreference(@PathVariable String email) {
        return ResponseEntity.ok(service.get(email));
    }

}
