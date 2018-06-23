package com.challenge.analysis.service;

import com.challenge.analysis.dto.ResponseDTO;

public interface HTMLAnalysis<T> {

    ResponseDTO<T> analyseHTML(String url);
}
