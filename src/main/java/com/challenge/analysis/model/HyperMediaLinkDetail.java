package com.challenge.analysis.model;

import com.challenge.analysis.util.LinkType;

public class HyperMediaLinkDetail {

    private String url;
    private LinkType linkType;
    private Boolean isReachable;
    private String comments;

    public HyperMediaLinkDetail(String url, LinkType linkType) {
        this.url = url;
        this.linkType = linkType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    public Boolean getReachable() {
        return isReachable;
    }

    public void setReachable(Boolean reachable) {
        isReachable = reachable;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        HyperMediaLinkDetail hyperMediaLinkDetail = (HyperMediaLinkDetail) obj;
        return url != null && url.equals(hyperMediaLinkDetail.getUrl());
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }
}
