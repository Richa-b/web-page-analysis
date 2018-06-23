package com.challenge.analysis.service;

import com.challenge.analysis.dto.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface HTMLAnalysis<T> {

    ResponseDTO<T> analyseHTML(String url);
}
