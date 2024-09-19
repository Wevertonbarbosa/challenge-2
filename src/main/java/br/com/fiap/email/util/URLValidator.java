package br.com.fiap.email.util;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLValidator {

    private static final String TEMPORARY_HOST_BIT = "bit.ly";
    private static final String TEMPORARY_HOST_TINY = "tinyurl.com";
    private static final String URL_PATTERN = "(http://|https://)[a-zA-Z0-9./?=_-]+";


    public static Set<String> extractURLs(String[] body) {
        Set<String> urls = new HashSet<>();
        Pattern pattern = Pattern.compile(URL_PATTERN);

        Arrays.stream(body).forEach(w -> {
            Matcher matcher = pattern.matcher(w);
            while (matcher.find()) {
                urls.add(matcher.group());
            }
        });
        return urls;
    }

    public static boolean isSuspiciousUrl(Set<String> urls) {

        for (String url : urls) {
            if (verifyIfUrlHasTemporaryDomain(url) || !hasValidSSL(url)) {
                return true;
            }
        }

        return false;
    }

    public static boolean verifyIfUrlHasTemporaryDomain(final String pUrl) {
        URL url = null;
        try {
            url = new URL(pUrl);
        } catch (Exception e) {
            //TODO EXCECAO TEMPORARIA
            throw new RuntimeException(e);
        }
        String host = url.getHost();
        return host.endsWith(TEMPORARY_HOST_BIT) || host.endsWith(TEMPORARY_HOST_TINY);

    }


    public static boolean hasValidSSL(final String pUrl) {
        try {
            URL url = new URL(pUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            return connection.getResponseCode() == 200;

        } catch (Exception e) {
            //TODO EXCECAO
            return false;
        }
    }
}
