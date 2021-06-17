package com.saransh.minifyurl.service;

import com.saransh.minifyurl.model.Url;

/**
 * Created by CryptoSingh1337 on 6/17/2021
 */
public interface UrlService {

    Url insert (String longUrl);
    Url findByShortUrl(String shortURL);
}
