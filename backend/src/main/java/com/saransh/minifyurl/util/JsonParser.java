package com.saransh.minifyurl.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CryptoSingh1337 on 6/17/2021
 */
@Component
public class JsonParser {

    private final String host;

    public JsonParser(@Value("${api.origin.url}") String host) {
        this.host = host;
    }

    public Map<String, String> parse(String longUrl, String shortUrl) {
        HashMap<String, String> map = new HashMap<>();
        map.put("longUrl", longUrl);
        map.put("shortUrl", host + shortUrl);
        return map;
    }
}
