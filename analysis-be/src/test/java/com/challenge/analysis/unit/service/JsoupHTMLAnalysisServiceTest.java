package com.challenge.analysis.unit.service;

import com.challenge.analysis.model.ResponseDTO;
import com.challenge.analysis.model.WebPageAnalysisInfo;
import com.challenge.analysis.service.JsoupHTMLAnalysisService;
import com.challenge.analysis.service.WebPageAccessibilityCheckerService;
import com.challenge.analysis.util.ConfigUtil;
import com.challenge.analysis.util.LinkType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
public class JsoupHTMLAnalysisServiceTest {

    private JsoupHTMLAnalysisService jsoupHTMLAnalysisService;
    private WebPageAccessibilityCheckerService webPageAccessibilityCheckerService;

    @Value("${login.form.actions.regex}")
    String formActionRegex;

    @Before
    public void setup() {
        jsoupHTMLAnalysisService = new JsoupHTMLAnalysisService();
        webPageAccessibilityCheckerService = Mockito.mock(WebPageAccessibilityCheckerService.class);
        ConfigUtil.FORM_ACTION_REGEX = ".*(/login|/session).*";
        ConfigUtil.USER_NAMES_LIST = Arrays.asList("user");
        ConfigUtil.PASSWORD_NAMES_LIST = Arrays.asList("password");
    }

    @Test
    public void invalidUrlTest() {
        ResponseDTO responseDTO = jsoupHTMLAnalysisService.analyseHTML("https://www.invalid.com");
        Assert.assertFalse(responseDTO.getStatus());
    }

    @Test
    public void isLoginFormExistPositive() {
        Document document = Jsoup.parse("<div><form action='/login' method='post'> <input type='text' name='user'> " +
                "<input type='password' name='password'></form> </div>");
        Assert.assertTrue(jsoupHTMLAnalysisService.isLoginFormExists(document));
    }

    @Test
    public void isLoginFormExistNegative() {
        Document document = Jsoup.parse("<div><form action='/loginForm' method='post'> <input type='text' name='user'> ");
        Assert.assertFalse(jsoupHTMLAnalysisService.isLoginFormExists(document));
    }

    @Test
    public void getHeadingCountTest() {
        Document document = Jsoup.parse("<h1>Test H1</h1> <h1>Test H1</h1> <h2>Test H1</h2><h3>Test H1</h3>" +
                "<h4>Test H4</h4>");
        Assert.assertEquals(2, (long) jsoupHTMLAnalysisService.getHeadingsCount(document).get("h1"));
        Assert.assertEquals(0, (long) jsoupHTMLAnalysisService.getHeadingsCount(document).get("h6"));
    }

    @Test
    public void createHyperLinks() {
        Document document = Jsoup.parse("<a href='https://www.test.com'> </a>");
        List<Element> anchorElementList = new ArrayList<>(document.select("a"));
        List<Element> mediaElementList = new ArrayList<>(document.select("img"));
        Assert.assertEquals(1, jsoupHTMLAnalysisService.createHyperMediaLinks(LinkType.ANCHOR, anchorElementList).size());
        Assert.assertEquals(0, jsoupHTMLAnalysisService.createHyperMediaLinks(LinkType.MEDIA, mediaElementList).size());

    }

    @Test
    public void getHTMLInfo() {
        Document document = Jsoup.parse("<!DOCTYPE html><title>Test Title</title>");
        WebPageAnalysisInfo webPageAnalysisInfo =
                jsoupHTMLAnalysisService.getHTMLInfo(document, "https://www.gmail.com");
        Assert.assertEquals(webPageAnalysisInfo.getTitle(), "Test Title");
        Assert.assertEquals(webPageAnalysisInfo.getHtmlVersion(), "HTML 5.0");
        Assert.assertEquals(webPageAnalysisInfo.getUrl(), "https://www.gmail.com");
    }

    @Test
    public void getHTMLVersion() {
        Document document1 = Jsoup.parse("<!DOCTYPE html><title>Test Title</title>");
        Document document2 = Jsoup.parse("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" ");
        Document document3 = Jsoup.parse("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" ");
        Assert.assertEquals(jsoupHTMLAnalysisService.getHtmlVersion(document1), "HTML 5.0");
        Assert.assertEquals(jsoupHTMLAnalysisService.getHtmlVersion(document2), "HTML 4.01//EN");
        Assert.assertEquals(jsoupHTMLAnalysisService.getHtmlVersion(document3), "XHTML 1.1//EN");

    }

    @Test
    public void fetchHyperMediaLinkDetail() {
        Document document = Jsoup.parse("<!DOCTYPE html><title>Test Title</title>");
        Assert.assertEquals(0, jsoupHTMLAnalysisService.fetchHyperMediaLinkDetail(document, "https://www.gmail.com").size());

    }
}


