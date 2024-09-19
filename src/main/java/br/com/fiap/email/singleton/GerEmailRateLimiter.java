package br.com.fiap.email.singleton;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class GerEmailRateLimiter {

    private static GerEmailRateLimiter instance;

    private final int MAX_EMAILS = 5;
    private final int TIME_WINDOW_MINUTES = 10;

    private Map<String, List<Instant>> userEmailLogs = new HashMap<>();

    public static synchronized GerEmailRateLimiter getInstance() {
        if (instance == null) {
            instance = new GerEmailRateLimiter();
        }
        return instance;
    }

    public synchronized boolean canSendEmail(String userEmail) {
        List<Instant> emailTimestamps = userEmailLogs.getOrDefault(userEmail, new ArrayList<>());

        Instant now = Instant.now();
        emailTimestamps.removeIf(timestamp -> ChronoUnit.MINUTES.between(timestamp, now) > TIME_WINDOW_MINUTES);

        userEmailLogs.put(userEmail, emailTimestamps);

        if (emailTimestamps.size() < MAX_EMAILS) {
            emailTimestamps.add(now);
            return true;
        }
        return false;
    }
}

