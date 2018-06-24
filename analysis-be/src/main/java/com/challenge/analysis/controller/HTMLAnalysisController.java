package com.challenge.analysis.controller;

import com.challenge.analysis.model.WebPageAnalysisInfo;
import com.challenge.analysis.model.ResponseDTO;
import com.challenge.analysis.service.HTMLAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/analysis")
@CrossOrigin
public class HTMLAnalysisController {

    @Autowired
    HTMLAnalysis htmlAnalysis;

    @PostMapping
    public ResponseDTO<WebPageAnalysisInfo> analyseHTML(@RequestParam String url) {
        return htmlAnalysis.analyseHTML(url);
    }
}
