package com.challenge.analysis.service;

import com.challenge.analysis.dto.HTMLInfo;
import com.challenge.analysis.dto.ResponseDTO;
import com.challenge.analysis.util.HTMLAnalysisConstants;
import com.challenge.analysis.util.HTMLAnalysisUtil;
import com.challenge.analysis.util.HeadingLevel;
import com.challenge.analysis.util.HyperMediaLinkType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class JsoupHTMLAnalysisService implements HTMLAnalysis<HTMLInfo> {

    private final Logger log = LoggerFactory.getLogger(JsoupHTMLAnalysisService.class);

    public ResponseDTO<HTMLInfo> analyseHTML(String url) {
        ResponseDTO<HTMLInfo> responseDTO = new ResponseDTO<>();
        try {
            Document document = Jsoup.connect(url).get();
            responseDTO.setData(getHTMLInfo(document, url));
        } catch (IOException e) {
            log.error("Exception occurred while crawling Url");
            responseDTO.setErrorResponse(e, "Error Occurred while crawling URL " + url);
        }
        return responseDTO;
    }

    private HTMLInfo getHTMLInfo(Document document, String url) {
        HTMLInfo htmlInfo = HTMLInfo.builder()
                .setUrl(url)
                .setTitle(document.title())
                .setHtmlContent(document.html())
                .setHtmlVersion(getHtmlVersion(document))
                .setHeadingCountMap(getHeadingsCount(document))
                .setLinkTypeMap(null)
                .setContainLoginForm(false)
                .create();
        return htmlInfo;
    }

    private String getHtmlVersion(Document document) {
        List<Node> nodes = document.childNodes();
        Node node = nodes.stream()
                .filter(documentNode -> documentNode instanceof DocumentType)
                .findFirst().orElse(null);
        return generateHtmlVersion(node);
    }

    private String generateHtmlVersion(Node node) {
        String htmlVersion = "UNIDENTIFIED";
        if (Objects.nonNull(node)) {
            DocumentType documentType = (DocumentType) node;
            String publicId = documentType.attr("publicid");
            if (Objects.isNull(publicId)) {
                return "HTML 5.0";
            } else if (publicId.contains("XHTML")) {
                return publicId.substring(publicId.indexOf("XHTML"));
            } else if (publicId.contains("HTML")) {
                return publicId.substring(publicId.indexOf("HTML"));
            }
        }
        return htmlVersion;
    }

    private Map<HeadingLevel, Integer> getHeadingsCount(Document document) {
        Map<HeadingLevel, Integer> headingCountMap = new HashMap<>();
        Arrays.stream(HeadingLevel.values()).forEach(headingLevel -> {
                    headingCountMap.put(headingLevel, document.select(headingLevel.getTagName()).size());
                }
        );
        return headingCountMap;
    }

    private Map<HyperMediaLinkType, Integer> getLinksCount(Document document, String url) {
        Map<HyperMediaLinkType, Integer> linkTypeMap = new HashMap<>();
        List<Element> anchorElements = document.select(HTMLAnalysisConstants.ANCHOR_TAG);
        anchorElements.forEach(link -> {
            String absUrl = link.absUrl(HTMLAnalysisConstants.HREF_ATTRIBUTE);
            if (HTMLAnalysisUtil.isInternalDomain(url, absUrl)) {
                linkTypeMap.put(HyperMediaLinkType.INTERNAL, 1);
            } else {
                linkTypeMap.put(HyperMediaLinkType.EXTERNAL, 1);
            }
        });
        return null;
    }
}
