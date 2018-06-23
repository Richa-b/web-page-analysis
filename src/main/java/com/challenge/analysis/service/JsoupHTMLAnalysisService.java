package com.challenge.analysis.service;

import com.challenge.analysis.dto.HTMLInfo;
import com.challenge.analysis.dto.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class JsoupHTMLAnalysisService implements HTMLAnalysis<HTMLInfo>{

    public ResponseDTO<HTMLInfo> analyseHTML(String url) {
        return null;
    }
}
