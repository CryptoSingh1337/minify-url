package com.saransh.minifyurl.repository;

import com.saransh.minifyurl.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */

public interface UrlRepository extends MongoRepository<Url, String> {

    Url findByShortUrl(String shortUrl);
}
