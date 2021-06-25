package com.saransh.minifyurl.controllers;

import com.saransh.minifyurl.model.Url;
import com.saransh.minifyurl.service.UrlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RedirectControllerTest {

    @Mock
    private UrlServiceImpl service;
    private MockMvc mockMvc;
    private final String LONG_URL = "https://www.youtube.com/";
    private final String SHORT_URL = "dba51bcc52";
    private final String BAD_SHORT_URL = "dba51b";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RedirectController controller = new RedirectController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void redirect() throws Exception {
        Url url = Url.builder().longUrl(LONG_URL).shortUrl(SHORT_URL).build();
        when(service.findByShortUrl(anyString())).thenReturn(url);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/{shortUrl}", SHORT_URL))
                .andExpect(header().string("Location", LONG_URL))
                .andExpect(status().isPermanentRedirect());

        verify(service, times(1)).findByShortUrl(anyString());
    }

    @Test
    void redirectBadRequest() throws Exception {
        when(service.findByShortUrl(anyString())).thenReturn(null);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/{shortUrl}", BAD_SHORT_URL))
                .andExpect(status().isBadRequest());

        verify(service, times(1)).findByShortUrl(anyString());
    }
}