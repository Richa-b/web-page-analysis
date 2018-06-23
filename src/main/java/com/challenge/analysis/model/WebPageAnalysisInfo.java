package com.challenge.analysis.model;

import com.challenge.analysis.util.HyperMediaLinkGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

public class WebPageAnalysisInfo {

    private String url;

    @JsonIgnore
    private String htmlContent;

    private String htmlVersion;
    private String title;

    private Map<String, Integer> headingCountMap;

    public Map<HyperMediaLinkGroup, Set<HyperMediaLinkDetail>> getLinkTypeMap() {
        return linkTypeMap;
    }

    public void setLinkTypeMap(Map<HyperMediaLinkGroup, Set<HyperMediaLinkDetail>> linkTypeMap) {
        this.linkTypeMap = linkTypeMap;
    }

    private Map<HyperMediaLinkGroup, Set<HyperMediaLinkDetail>> linkTypeMap;

    private Boolean containLoginForm;

    private WebPageAnalysisInfo() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getHtmlVersion() {
        return htmlVersion;
    }

    public void setHtmlVersion(String htmlVersion) {
        this.htmlVersion = htmlVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Integer> getHeadingCountMap() {
        return headingCountMap;
    }

    public void setHeadingCountMap(Map<String, Integer> headingCountMap) {
        this.headingCountMap = headingCountMap;
    }


    public Boolean getContainLoginForm() {
        return containLoginForm;
    }

    public void setContainLoginForm(Boolean containLoginForm) {
        this.containLoginForm = containLoginForm;
    }

    private WebPageAnalysisInfo(String url, String htmlContent, String htmlVersion, String title, Map<String, Integer> headingCountMap, Map<HyperMediaLinkGroup, Set<HyperMediaLinkDetail>> linkTypeMap, Boolean containLoginForm) {
        this.url = url;
        this.htmlContent = htmlContent;
        this.htmlVersion = htmlVersion;
        this.title = title;
        this.headingCountMap = headingCountMap;
        this.linkTypeMap = linkTypeMap;
        this.containLoginForm = containLoginForm;
    }

    public static HTMLInfoBuilder builder() {
        return new HTMLInfoBuilder();
    }

    @Getter
    public static class HTMLInfoBuilder {

        private String url;

        private String htmlContent;

        private String htmlVersion;
        private String title;

        public HTMLInfoBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public HTMLInfoBuilder setHtmlContent(String htmlContent) {
            this.htmlContent = htmlContent;
            return this;
        }

        public HTMLInfoBuilder setHtmlVersion(String htmlVersion) {
            this.htmlVersion = htmlVersion;
            return this;
        }

        public HTMLInfoBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public HTMLInfoBuilder setHeadingCountMap(Map<String, Integer> headingCountMap) {
            this.headingCountMap = headingCountMap;
            return this;
        }

        public HTMLInfoBuilder setLinkTypeMap(Map<HyperMediaLinkGroup, Set<HyperMediaLinkDetail>> linkTypeMap) {
            this.linkTypeMap = linkTypeMap;
            return this;
        }

        public HTMLInfoBuilder setContainLoginForm(Boolean containLoginForm) {
            this.containLoginForm = containLoginForm;
            return this;
        }

        private Map<String, Integer> headingCountMap;

        private Map<HyperMediaLinkGroup, Set<HyperMediaLinkDetail>> linkTypeMap;

        private Boolean containLoginForm;


        public WebPageAnalysisInfo create() {
            return new WebPageAnalysisInfo(url, htmlContent, htmlVersion, title, headingCountMap, linkTypeMap, containLoginForm);
        }


    }


}
