package com.challenge.analysis.controller;

import com.challenge.analysis.model.WebPageAnalysisInfo;
import com.challenge.analysis.model.ResponseDTO;
import com.challenge.analysis.service.HTMLAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/analyse")
@CrossOrigin
public class HTMLAnalysisController {

    @Autowired
    HTMLAnalysis htmlAnalysis;

    @GetMapping
    public ResponseDTO<WebPageAnalysisInfo> analyseHTML(@RequestParam String url) {
        return htmlAnalysis.analyseHTML(url);
    }
}
