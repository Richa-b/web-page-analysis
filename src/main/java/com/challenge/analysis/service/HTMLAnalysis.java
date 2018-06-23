package com.challenge.analysis.service;

import com.challenge.analysis.model.ResponseDTO;

public interface HTMLAnalysis<T> {

    ResponseDTO<T> analyseHTML(String url);
}
