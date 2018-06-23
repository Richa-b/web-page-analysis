package com.challenge.analysis.service;

import com.challenge.analysis.dto.HTMLInfo;
import com.challenge.analysis.dto.ResponseDTO;
import com.challenge.analysis.util.HTMLAnalysisConstants;
import com.challenge.analysis.util.HTMLAnalysisUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class JsoupHTMLAnalysisService implements HTMLAnalysis<HTMLInfo> {

    private final Logger log = LoggerFactory.getLogger(JsoupHTMLAnalysisService.class);

    public ResponseDTO<HTMLInfo> analyseHTML(String currentUrl) {
        log.info("-> analyseHTML :: Url=" + currentUrl);
        ResponseDTO<HTMLInfo> responseDTO = new ResponseDTO<>();
        try {
            Document document = Jsoup.connect(currentUrl).get();
            responseDTO.setData(getHTMLInfo(document, currentUrl));
        } catch (IOException e) {
            log.error("Exception occurred while accessing Url");
            responseDTO.setErrorResponse(e, "Error Occurred while accessing URL " + currentUrl + " " + e.getMessage());
        }
        log.info("<- analyseHTML:: ResponseDTO=> status=" + responseDTO.getStatus());
        return responseDTO;
    }

    private HTMLInfo getHTMLInfo(Document document, String currentUrl) {
        return HTMLInfo.builder()
                .setUrl(currentUrl)
                .setTitle(document.title())
                .setHtmlContent(document.html())
                .setHtmlVersion(getHtmlVersion(document))
                .setHeadingCountMap(getHeadingsCount(document))
                .setLinkTypeMap(getLinksCount(document, currentUrl))
                .setContainLoginForm(false)
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

    private Map<String, Integer> getLinksCount(Document document, String currentUrl) {
        Map<String, Integer> linkTypeMap = new HashMap<>();
        String currentDomain = HTMLAnalysisUtil.getDomainName(currentUrl);
        List<Element> anchorElements = document.select(HTMLAnalysisConstants.ANCHOR_TAG);
        anchorElements.forEach(link ->
                populateLinkCountMap(link.absUrl(HTMLAnalysisConstants.HREF_ATTRIBUTE), currentDomain, linkTypeMap)
        );
        return linkTypeMap;
    }

    private void populateLinkCountMap(String absUrl, String currentDomain, Map<String, Integer> linkTypeMap) {
        String key = HTMLAnalysisUtil.isInternalDomain(currentDomain, absUrl) ?
                HTMLAnalysisConstants.INTERNAL_LINK :
                HTMLAnalysisConstants.EXTERNAL_LINK;
        HTMLAnalysisUtil.maintainCountMap(linkTypeMap, key);
    }
}
