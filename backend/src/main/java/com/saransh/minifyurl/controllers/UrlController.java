package com.saransh.minifyurl.controllers;

import com.saransh.minifyurl.error.CustomErrorType;
import com.saransh.minifyurl.model.Url;
import com.saransh.minifyurl.service.UrlService;
import com.saransh.minifyurl.util.JsonParser;
import com.saransh.minifyurl.util.VerifyUrl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
@Controller
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;
    private final JsonParser parser;

    public UrlController(UrlService urlService, JsonParser parser) {
        this.urlService = urlService;
        this.parser = parser;
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addUrl(@RequestParam("longUrl") String longUrl) {
        if (VerifyUrl.isValid(longUrl)) {
            Url savedUrl = urlService.insert(longUrl);
            return new ResponseEntity<>(parser.parse(savedUrl.getLongUrl(), savedUrl.getShortUrl()), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new CustomErrorType("Url is not valid"), HttpStatus.BAD_REQUEST);
    }
}
