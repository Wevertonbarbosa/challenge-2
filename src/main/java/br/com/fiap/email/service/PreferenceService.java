package br.com.fiap.email.service;

import br.com.fiap.email.dto.AddPreferenceInPutDto;
import br.com.fiap.email.dto.GetPreferenceOutPutDto;
import br.com.fiap.email.exception.ObjectNotFoundException;
import br.com.fiap.email.model.Preference;
import br.com.fiap.email.repository.PreferenceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PreferenceService {


    PreferenceRepository repository;

    public PreferenceService(PreferenceRepository repository) {
        this.repository = repository;
    }

    public void add(final AddPreferenceInPutDto dto) {
        Preference preference = new Preference();
        BeanUtils.copyProperties(dto, preference);
        if(repository.existsById(preference.getEmail())){
            repository.deleteById(preference.getEmail());
        }
        repository.save(preference);
    }

    public GetPreferenceOutPutDto get(final String email) {
        Preference preference = repository.findById(email).orElseThrow(() -> new ObjectNotFoundException(""));
        return new GetPreferenceOutPutDto(preference);
    }
}
