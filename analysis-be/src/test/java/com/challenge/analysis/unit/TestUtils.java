package com.challenge.analysis.unit;

import com.challenge.analysis.model.WebPageAnalysisInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestUtils {

    public static WebPageAnalysisInfo getWebPageAnalysisInfo() {
        return new WebPageAnalysisInfo.HTMLInfoBuilder()
                .setTitle("Test Title")
                .create();
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }


    public static <T> String objectToJson(T object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }


}
