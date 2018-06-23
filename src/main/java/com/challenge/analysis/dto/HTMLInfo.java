package com.challenge.analysis.dto;

import com.challenge.analysis.util.HeadingLevel;
import com.challenge.analysis.util.HyperMediaLinkType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.Map;

public class HTMLInfo {

    private String url;

    @JsonIgnore
    private String htmlContent;

    private String htmlVersion;
    private String title;

    private Map<HeadingLevel, Integer> headingCountMap;

    private Map<HyperMediaLinkType, Integer> linkTypeMap;

    private Boolean containLoginForm;

    private HTMLInfo() {
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

    public Map<HeadingLevel, Integer> getHeadingCountMap() {
        return headingCountMap;
    }

    public void setHeadingCountMap(Map<HeadingLevel, Integer> headingCountMap) {
        this.headingCountMap = headingCountMap;
    }

    public Map<HyperMediaLinkType, Integer> getLinkTypeMap() {
        return linkTypeMap;
    }

    public void setLinkTypeMap(Map<HyperMediaLinkType, Integer> linkTypeMap) {
        this.linkTypeMap = linkTypeMap;
    }

    public Boolean getContainLoginForm() {
        return containLoginForm;
    }

    public void setContainLoginForm(Boolean containLoginForm) {
        this.containLoginForm = containLoginForm;
    }

    private HTMLInfo(String url, String htmlContent, String htmlVersion, String title, Map<HeadingLevel, Integer> headingCountMap, Map<HyperMediaLinkType, Integer> linkTypeMap, Boolean containLoginForm) {
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

        public HTMLInfoBuilder setHeadingCountMap(Map<HeadingLevel, Integer> headingCountMap) {
            this.headingCountMap = headingCountMap;
            return this;
        }

        public HTMLInfoBuilder setLinkTypeMap(Map<HyperMediaLinkType, Integer> linkTypeMap) {
            this.linkTypeMap = linkTypeMap;
            return this;
        }

        public HTMLInfoBuilder setContainLoginForm(Boolean containLoginForm) {
            this.containLoginForm = containLoginForm;
            return this;
        }

        private Map<HeadingLevel, Integer> headingCountMap;

        private Map<HyperMediaLinkType, Integer> linkTypeMap;

        private Boolean containLoginForm;


        public HTMLInfo create() {
            return new HTMLInfo(url, htmlContent, htmlVersion, title, headingCountMap, linkTypeMap, containLoginForm);
        }


    }


}
