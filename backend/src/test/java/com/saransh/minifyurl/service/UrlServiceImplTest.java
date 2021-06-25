package com.saransh.minifyurl.service;

import com.saransh.minifyurl.model.Url;
import com.saransh.minifyurl.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrlServiceImplTest {

    @Mock
    private UrlRepository urlRepository;
    @InjectMocks
    private UrlServiceImpl service;
    private Url url;
    private final String SHORT_URL = "https://www.abc.com";

    @BeforeEach
    void setUp() {
        url = Url.builder()
                .id("123")
                .longUrl("https://www.youtube.com/")
                .build();
    }

    @Test
    void insert() {
        when(urlRepository.insert((Url) any())).thenReturn(url);

        Url savedUrl = service.insert(url.getLongUrl());
        assertNotNull(savedUrl);

        verify(urlRepository, times(1)).findByShortUrl(anyString());
        verify(urlRepository, times(1)).insert((Url) any());
    }

    @Test
    void insertExistingUrl() {
        when(urlRepository.findByShortUrl(anyString())).thenReturn(url);

        Url savedUrl = service.insert(url.getLongUrl());
        assertNotNull(savedUrl);

        verify(urlRepository, times(1)).findByShortUrl(anyString());
        verify(urlRepository, times(0)).insert((Url) any());
    }

    @Test
    void findByShortUrl() {
        when(urlRepository.findByShortUrl(anyString())).thenReturn(url);

        Url retrievedUrl = service.findByShortUrl(SHORT_URL);
        assertNotNull(retrievedUrl);
        verify(urlRepository, times(1)).findByShortUrl(anyString());
    }
}