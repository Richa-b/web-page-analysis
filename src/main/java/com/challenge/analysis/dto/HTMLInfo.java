package com.challenge.analysis.dto;

import com.challenge.analysis.util.HeadingLevel;
import com.challenge.analysis.util.HyperMediaLinkType;

import java.util.Map;

public class HTMLInfo {

    private String version;
    private String title;

    private Map<HeadingLevel, Integer> headingCountMap;

    private Map<HyperMediaLinkType, Integer> linkTypeMap;

    private Boolean containLoginForm;

}
