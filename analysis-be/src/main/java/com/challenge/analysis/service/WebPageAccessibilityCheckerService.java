package com.challenge.analysis.service;

import com.challenge.analysis.model.HyperMediaLinkDetail;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static com.challenge.analysis.util.ConfigUtil.CONNECTION_TIME_OUT_IN_MILLIS;

@Service
class WebPageAccessibilityCheckerService {
    private final Logger log = LoggerFactory.getLogger(WebPageAccessibilityCheckerService.class);

    @Autowired
    MessageSource messageSource;

    @Async("asyncExecutor")
    public CompletableFuture<HyperMediaLinkDetail> accessWebPageLinks(HyperMediaLinkDetail hyperMediaLinkDetail) {
        try {
            Jsoup.connect(hyperMediaLinkDetail.getUrl())
                    .timeout(CONNECTION_TIME_OUT_IN_MILLIS)
                    .ignoreContentType(true)
                    .execute();
            hyperMediaLinkDetail.setReachable(true);
            hyperMediaLinkDetail.setComments(messageSource.getMessage("default.accessible.link",
                    null, "Link is Reachable.", LocaleContextHolder.getLocale()))
            ;
        } catch (IOException e) {
            log.error("Error occurred while accessing link " + hyperMediaLinkDetail.getUrl(), e);
            hyperMediaLinkDetail.setReachable(false);
            hyperMediaLinkDetail.setComments(e.getMessage());
        }
        return CompletableFuture.completedFuture(hyperMediaLinkDetail);
    }
}