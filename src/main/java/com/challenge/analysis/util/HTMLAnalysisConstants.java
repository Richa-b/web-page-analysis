package com.challenge.analysis.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class HTMLAnalysisConstants {

    public static String WWW = "www.";
    public static String ANCHOR_TAG = "a";
    public static String IMG_TAG = "img";
    public static String VIDEO_TAG = "source";
    public static String PUBLIC_ID = "publicid";
    public static String UNIDENTIFIED = "Unidentified";
    public static String HTML_5 = "HTML 5.0";
    public static String HTML = "HTML";
    public static String XHTML = "XHTML";
    public static String FORM_ELEMENT = "form";
    public static String VALID_FORMS = "validForms";
    public static String POST_METHOD = "post";
    public static String HREF_ATTRIBUTE = "href";
    public static String METHOD_ATTRIBUTE = "method";
    public static String ACTION_ATTRIBUTE = "action";
    public static String NAME_ATTRIBUTE = "name";
    public static String TYPE_ATTRIBUTE = "type";
    public static String SOURCE_ATTRIBUTE = "src";
    public static String INPUT_ELEMENT = "input";
    public static String TEXT = "text";
    public static String EMAIL = "email";
    public static String PASSWORD = "password";

    public static Pattern FORM_ACTION_PATTERN = Pattern.compile(ConfigUtil.FORM_ACTION_REGEX);

    public static List<String> headingLevels = Arrays.asList("h1", "h2", "h3", "h4", "h5", "h6");

}
