package com.challenge.analysis.unit.controller;


import com.challenge.analysis.controller.HTMLAnalysisController;
import com.challenge.analysis.model.ResponseDTO;
import com.challenge.analysis.model.WebPageAnalysisInfo;
import com.challenge.analysis.service.HTMLAnalysis;
import com.challenge.analysis.unit.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(HTMLAnalysisController.class)
public class HTMLAnalysisControllerTest {

    @MockBean
    HTMLAnalysis htmlAnalysis;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void analyseHTML() throws Exception {
        ResponseDTO<WebPageAnalysisInfo> responseDTO = new ResponseDTO<>();
        responseDTO.setData(TestUtils.getWebPageAnalysisInfo());
        Mockito.when(htmlAnalysis.analyseHTML(Mockito.anyString())).thenReturn(responseDTO);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/analyse")
                        .param("url", "https://github.com/login")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        // verify that service method was called once
        verify(htmlAnalysis).analyseHTML(any(String.class));

        ResponseDTO<WebPageAnalysisInfo> responseDTO1 = TestUtils.jsonToObject(result.getResponse()
                .getContentAsString(), ResponseDTO.class);
        assertNotNull(responseDTO1);
        assertEquals(true, responseDTO1.getStatus());
    }
}
