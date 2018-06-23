package com.challenge.analysis.service;

import com.challenge.analysis.model.HyperMediaLinkDetail;
import com.challenge.analysis.model.ResponseDTO;
import com.challenge.analysis.model.WebPageAnalysisInfo;
import com.challenge.analysis.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.challenge.analysis.util.ConfigUtil.CONNECTION_TIME_OUT_IN_MILLIS;
import static java.util.stream.Collectors.groupingBy;

@Service
public class JsoupHTMLAnalysisService implements HTMLAnalysis<WebPageAnalysisInfo> {

    private final Logger log = LoggerFactory.getLogger(JsoupHTMLAnalysisService.class);

    public ResponseDTO<WebPageAnalysisInfo> analyseHTML(String currentUrl) {
        log.info("-> analyseHTML :: Url=" + currentUrl);
        ResponseDTO<WebPageAnalysisInfo> responseDTO = new ResponseDTO<>();
        try {
            Document document = Jsoup.connect(currentUrl).timeout(ConfigUtil.CONNECTION_TIME_OUT_IN_MILLIS).get();
            responseDTO.setData(getHTMLInfo(document, currentUrl));
        } catch (IOException e) {
            log.error("Exception occurred while accessing Url");
            responseDTO.setErrorResponse(e, "Error Occurred while accessing URL " + currentUrl + " " + e.getMessage());
        }
        log.info("<- analyseHTML:: ResponseDTO=> status=" + responseDTO.getStatus());
        return responseDTO;
    }

    private WebPageAnalysisInfo getHTMLInfo(Document document, String currentUrl) {
        return WebPageAnalysisInfo.builder()
                .setUrl(currentUrl)
                .setTitle(document.title())
                .setHtmlContent(document.html())
                .setHtmlVersion(getHtmlVersion(document))
                .setHeadingCountMap(getHeadingsCount(document))
                .setLinkTypeMap(fetchHyperMediaLinkDetail(document, currentUrl))
                .setContainLoginForm(isLoginFormExists(document))
                .create();
    }

    private String getHtmlVersion(Document document) {
        List<Node> nodes = document.childNodes();
        Node node = nodes.stream()
                .filter(documentNode -> documentNode instanceof DocumentType)
                .findFirst().orElse(null);
        return generateHtmlVersion(node);
    }

    private String generateHtmlVersion(Node node) {
        String htmlVersion = HTMLAnalysisConstants.UNIDENTIFIED;
        if (Objects.nonNull(node)) {
            DocumentType documentType = (DocumentType) node;
            String publicId = documentType.attr(HTMLAnalysisConstants.PUBLIC_ID);
            if (StringUtils.isEmpty(publicId)) {
                return HTMLAnalysisConstants.HTML_5;
            } else if (publicId.contains(HTMLAnalysisConstants.XHTML)) {
                return publicId.substring(publicId.indexOf(HTMLAnalysisConstants.XHTML));
            } else if (publicId.contains(HTMLAnalysisConstants.HTML)) {
                return publicId.substring(publicId.indexOf(HTMLAnalysisConstants.HTML));
            }
        }
        return htmlVersion;
    }

    private Map<String, Integer> getHeadingsCount(Document document) {
        Map<String, Integer> headingCountMap = new HashMap<>();
        HTMLAnalysisConstants.headingLevels.forEach(headingLevel ->
                headingCountMap.put(headingLevel, document.select(headingLevel).size())
        );
        return headingCountMap;
    }

    private Map<HyperMediaLinkGroup, Set<HyperMediaLinkDetail>> fetchHyperMediaLinkDetail(Document document, String currentUrl) {
        String currentDomain = HTMLAnalysisUtil.getDomainName(currentUrl);

        Set<HyperMediaLinkDetail> hyperMediaLinkDetails = new HashSet<>();
        Arrays.stream(LinkType.values()).forEach(linkType ->
                hyperMediaLinkDetails.addAll(createHyperMediaLinks
                        (linkType, document.select(linkType.getTag()))));

        return populateAccessibilityInfoForHyperMediaLinks(hyperMediaLinkDetails, currentDomain);
    }

    private Map<HyperMediaLinkGroup, Set<HyperMediaLinkDetail>> populateAccessibilityInfoForHyperMediaLinks(Set<HyperMediaLinkDetail> hyperMediaLinkDetails, String currentDomain) {

        hyperMediaLinkDetails.forEach(hyperMediaLinkDetail -> {
            try {
                log.info("Accessing " + hyperMediaLinkDetail.getUrl());
                Jsoup.connect(hyperMediaLinkDetail.getUrl())
                        .timeout(CONNECTION_TIME_OUT_IN_MILLIS)
                        .ignoreContentType(true)
                        .execute();
                hyperMediaLinkDetail.setReachable(true);
                hyperMediaLinkDetail.setComments("Link Is Reachable");
            } catch (IOException e) {
                log.error("Error occurred while accessing link " + hyperMediaLinkDetail.getUrl(), e);
                hyperMediaLinkDetail.setReachable(false);
                hyperMediaLinkDetail.setComments(e.getMessage());
            }
        });

        return hyperMediaLinkDetails.stream()
                .collect(groupingBy(hyperMediaLinkDetail ->
                        HyperMediaLinkGroup.getLinkGroup(hyperMediaLinkDetail, currentDomain), Collectors.toSet()));
    }

    private Set<HyperMediaLinkDetail> createHyperMediaLinks(LinkType linkType, List<Element> elements) {
        return elements.stream()
                .filter(element -> !StringUtils.isEmpty(element.absUrl(linkType.getAttribute())))
                .map(element -> new
                        HyperMediaLinkDetail(element.absUrl(linkType.getAttribute()), linkType))
                .collect(Collectors.toSet());
    }

    private Boolean isLoginFormExists(Document document) {
        Map<String, List<Element>> contextMap = new HashMap<>();
        return Arrays.stream(FormValidationRule.values())
                .noneMatch(formValidationRule -> Boolean.FALSE.equals(formValidationRule.validate(contextMap, document)));
    }
}
