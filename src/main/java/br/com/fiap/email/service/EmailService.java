package br.com.fiap.email.service;

import br.com.fiap.email.dto.DetailEmailOutPutDto;
import br.com.fiap.email.dto.EmailInPutDto;
import br.com.fiap.email.dto.EmailOutPutDto;
import br.com.fiap.email.exception.ObjectNotFoundException;
import br.com.fiap.email.model.Email;
import br.com.fiap.email.repository.EmailRepository;
import br.com.fiap.email.singleton.GerEmailRateLimiter;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;
    private SendSpamService spamService;

    public ResponseEntity<HttpStatus> postEmail(EmailInPutDto emailInPutDto) {

        if(!GerEmailRateLimiter.getInstance().canSendEmail(emailInPutDto.sender())){
            throw new RuntimeException("You passed the limit of rate of send emails");
        }

        Email emailSalvo = new Email();
        BeanUtils.copyProperties(emailInPutDto, emailSalvo);
        emailSalvo.setInbox(false);
        emailSalvo.setDate(LocalDateTime.now());
        Email email = emailRepository.save(emailSalvo);
        if(!spamService.spamVerify(email)){
            throw new RuntimeException("Spam");
        }
        return new ResponseEntity<>(sendMailMock(email));
    }

    public DetailEmailOutPutDto findEmailById(Long id) {
        Optional<Email> emailOptional = emailRepository.findById(id);
        if (emailOptional.isPresent()) {
            return new DetailEmailOutPutDto(emailOptional.get());
        } else {
            throw new ObjectNotFoundException("Email não encontrado no Banco de dados!");
        }
    }

    public List<EmailOutPutDto> listEmail() {
        return emailRepository.findAll()
                .stream()
                .filter(Email :: isInbox)
                .map(EmailOutPutDto::new)
                .toList();
    }

    private HttpStatus sendMailMock(Email email){
        return HttpStatus.OK;
    }


    public List<EmailOutPutDto> listEmailByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        Optional<List<Email>> emails = emailRepository.findAllByDateRange(startOfDay, endOfDay);
        if(emails.isPresent()){
            return emails.get().stream()
                    .filter(Email :: isInbox)
                    .map(EmailOutPutDto::new)
                    .toList();
        }
        return new ArrayList<EmailOutPutDto>();
    }

}
