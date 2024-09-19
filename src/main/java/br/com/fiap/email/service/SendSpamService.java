package br.com.fiap.email.service;

import br.com.fiap.email.enums.LeveOfThreadPerWordEnum;
import br.com.fiap.email.model.BlackListSpamWords;
import br.com.fiap.email.model.Email;
import br.com.fiap.email.model.EmailCC;
import br.com.fiap.email.repository.BlackListWordsRepository;
import br.com.fiap.email.repository.EmailCCRepository;
import br.com.fiap.email.util.StringEmailUtil;
import br.com.fiap.email.util.URLValidator;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SendSpamService {

    private final BlackListWordsRepository blackListWordsRepository;
    private final EmailCCRepository ccRepository;

    public SendSpamService(BlackListWordsRepository blackListWordsRepository, EmailCCRepository ccRepository) {
        this.blackListWordsRepository = blackListWordsRepository;
        this.ccRepository = ccRepository;
    }

    public boolean spamVerify(final Email email) {
        boolean sendEmail = false;
        String[] subjectWords = StringEmailUtil.getArrayStringsEmail(email.getSubject());
        String[] bodyWords = StringEmailUtil.getArrayStringsEmail(email.getBody());

        List<BlackListSpamWords> blackListSpamWords = blackListWordsRepository.findAll();
        Set<String> spamWordSet = StringEmailUtil.spamWordsToSet(blackListSpamWords);

        Map<String, Integer> wordAppearance = StringEmailUtil.mapTimesAppearanceWord(subjectWords, bodyWords, spamWordSet);

        int totalPointsOfThreat = sumTotalPointsOfThreat(wordAppearance, blackListSpamWords);

        LeveOfThreadPerWordEnum level = getThreatLevel(totalPointsOfThreat);

        if (isDangerousLevel(email, level)) {
            return sendEmail;
        }

        if (verifyUrls(bodyWords)) {
            return sendEmail;
        }

        return true;
    }

    private static boolean verifyUrls(String[] bodyWords) {
        Set<String> urlsExtracted = URLValidator.extractURLs(bodyWords);

        if (!urlsExtracted.isEmpty()) {
            return URLValidator.isSuspiciousUrl(urlsExtracted);
        }
        return false;
    }

    private boolean isDangerousLevel(final Email email, final LeveOfThreadPerWordEnum level) {
        if (level == LeveOfThreadPerWordEnum.DANGEROUS) {
            return true;
        } else if (level == LeveOfThreadPerWordEnum.HIGH) {
            Optional<List<EmailCC>> emailCC = ccRepository.findByIdEmailId(email.getId());
            return emailCC.isPresent() && emailCC.get().size() > 10;
        }
        return false;
    }


    private static int sumTotalPointsOfThreat(Map<String, Integer> wordAppearance, List<BlackListSpamWords> blackListSpamWords) {
        int totalPointsOfThreat = 0;
        for (Map.Entry<String, Integer> entry : wordAppearance.entrySet()) {
            BlackListSpamWords word = blackListSpamWords.stream().filter(o -> o.getWord().equalsIgnoreCase(entry.getKey())).findFirst().get();
            int pointsOfThreatPerWord = word.getThreatLevel() * entry.getValue();
            totalPointsOfThreat = totalPointsOfThreat + pointsOfThreatPerWord;
        }
        return totalPointsOfThreat;
    }


    public LeveOfThreadPerWordEnum getThreatLevel(int totalPoints) {
        return Arrays.stream(LeveOfThreadPerWordEnum.values())
                .filter(level -> totalPoints >= level.getPoints())
                .findFirst()
                .orElse(LeveOfThreadPerWordEnum.NORMAL);
    }
}
