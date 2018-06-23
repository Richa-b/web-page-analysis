package com.challenge.analysis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class HTMLAnalysisUtil {

    private static final Logger log = LoggerFactory.getLogger(HTMLAnalysisUtil.class);

    public static String getDomainName(String url) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            log.error("Error Occurred while parsing url " + url, e);
            return null;
        }
        String hostName = uri.getHost();
        if (Objects.nonNull(hostName)) {
            return hostName.startsWith(HTMLAnalysisConstants.WWW) ? hostName.substring(4) : hostName;
        }
        return hostName;
    }

    public static Boolean isInternalDomain(String currentDomain, String url) {
        String domainToBeVerified = getDomainName(url);
        return currentDomain.equals(domainToBeVerified);
    }

}
