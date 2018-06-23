package com.challenge.analysis.util;

public enum HeadingLevel {

    H1("h1"),
    H2("h2"),
    H3("h3"),
    H4("h4"),
    H5("h5"),
    H6("h6");

    private String tagName;

    public String getTagName() {
        return tagName;
    }

    HeadingLevel(String tagName) {
        this.tagName = tagName;
    }
}