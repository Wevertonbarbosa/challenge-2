package br.com.fiap.email.service;

import br.com.fiap.email.dto.EmailDto;
import br.com.fiap.email.dto.EmailExibicaoDto;
import br.com.fiap.email.model.Email;
import br.com.fiap.email.repository.EmailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public EmailExibicaoDto postEmail(EmailDto emailDto) {
        Email emailSalvo = new Email();
        BeanUtils.copyProperties(emailDto, emailSalvo);

        Email email = emailRepository.save(emailSalvo);
        return new EmailExibicaoDto(email);
    }

    public EmailExibicaoDto searchEmailbyId(Long id) {
        Optional<Email> emailOptional = emailRepository.findById(id);
        if (emailOptional.isPresent()) {
            return new EmailExibicaoDto(emailOptional.get());
        } else {
            throw new RuntimeException("Email não encontrado no Banco de dados!");
        }
    }

    public List<EmailExibicaoDto> listEmail() {
        return emailRepository.findAll()
                .stream()
                .map(EmailExibicaoDto::new)
                .toList();
    }


}
