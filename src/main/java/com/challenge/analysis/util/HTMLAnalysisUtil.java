package com.challenge.analysis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;

public class HTMLAnalysisUtil {

    private static final Logger log = LoggerFactory.getLogger(HTMLAnalysisUtil.class);

    public static String getDomainName(String absoluteUrl) {
        URI uri;
        try {
            uri = new URI(absoluteUrl);
        } catch (URISyntaxException e) {
            log.error("Error Occurred while parsing url " + absoluteUrl, e);
            return null;
        }
        String hostName = uri.getHost();
        if (Objects.nonNull(hostName)) {
            return hostName.startsWith(HTMLAnalysisConstants.WWW) ? hostName.substring(4) : hostName;
        }
        return hostName;
    }

    public static Boolean isInternalDomain(String currentDomain, String absoluteUrl) {
        String domainToBeVerified = getDomainName(absoluteUrl);
        return  currentDomain.equals(domainToBeVerified) || (Objects.nonNull(domainToBeVerified) &&
                domainToBeVerified.endsWith(currentDomain));
    }

    public static void maintainCountMap(Map<String, Integer> countMap, String key) {
        if (countMap.containsKey(key)) {
            Integer count = countMap.get(key);
            countMap.put(key, count + 1);
        } else {
            countMap.put(key, 1);
        }
    }

}
