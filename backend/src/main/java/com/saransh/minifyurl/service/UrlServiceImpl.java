package com.saransh.minifyurl.service;

import com.saransh.minifyurl.model.Url;
import com.saransh.minifyurl.repository.UrlRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by CryptoSingh1337 on 6/17/2021
 */
@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Url insert(String longUrl) {
        String shortUrl = DigestUtils.md5Hex(longUrl).substring(0, 10);
        Url url = findByShortUrl(shortUrl);
        if (url == null) {
            Url saveUrl = new Url();
            saveUrl.setLongUrl(longUrl);
            saveUrl.setShortUrl(shortUrl);
            return urlRepository.insert(saveUrl);
        }
        return url;
    }

    @Override
    public Url findByShortUrl(String shortURL) {
        return urlRepository.findByShortUrl(shortURL);
    }
}
