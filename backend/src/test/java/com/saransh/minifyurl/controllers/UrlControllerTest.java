package com.saransh.minifyurl.controllers;

import com.saransh.minifyurl.model.Url;
import com.saransh.minifyurl.service.UrlServiceImpl;
import com.saransh.minifyurl.util.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UrlControllerTest {

    @Mock
    private UrlServiceImpl service;
    @Mock
    private JsonParser parser;
    private MockMvc mockMvc;
    private final String LONG_URL = "https://www.youtube.com/";
    private final String SHORT_URL = "dba51bcc52";
    private final String BAD_LONG_URL = "https:/ww.google.com/";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        UrlController controller = new UrlController(service, parser);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void addUrlIndexPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void addUrl() throws Exception {
        Url savedUrl = Url.builder().longUrl(LONG_URL).shortUrl(SHORT_URL).build();
        when(service.insert(anyString())).thenReturn(savedUrl);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("url", LONG_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        verify(service, times(1)).insert(anyString());
        verify(parser, times(1)).parse(anyString(), anyString());
    }

    @Test
    void addUrlBadRequest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("url", BAD_LONG_URL))
                .andExpect(status().isBadRequest());

        verify(service, times(0)).insert(anyString());
        verify(parser, times(0)).parse(anyString(), anyString());
    }
}