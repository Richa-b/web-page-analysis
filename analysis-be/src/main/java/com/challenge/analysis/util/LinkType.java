package com.challenge.analysis.util;

public enum LinkType {
    MEDIA(HTMLAnalysisConstants.IMG_TAG + "," + HTMLAnalysisConstants.VIDEO_TAG, HTMLAnalysisConstants.SOURCE_ATTRIBUTE),
    ANCHOR(HTMLAnalysisConstants.ANCHOR_TAG, HTMLAnalysisConstants.HREF_ATTRIBUTE);

    private String tag;
    private String attribute;

    public String getTag() {
        return tag;
    }

    public String getAttribute() {
        return attribute;
    }

    LinkType(String tag, String attribute) {
        this.tag = tag;
        this.attribute = attribute;
    }
}
