package com.challenge.analysis.controller;

import com.challenge.analysis.dto.HTMLInfo;
import com.challenge.analysis.dto.ResponseDTO;
import com.challenge.analysis.service.HTMLAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/analysis")
public class HTMLAnalysisController {

    @Autowired
    HTMLAnalysis htmlAnalysis;

    @PostMapping
    public ResponseDTO<HTMLInfo> analyseHTML(@RequestParam String url) {
        return htmlAnalysis.analyseHTML(url);
    }
}
