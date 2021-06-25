package com.saransh.minifyurl.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "urls")
public class Url {

    @Id
    private String id;
    @Field(name = "long_url")
    private String longUrl;
    @Field(name = "short_url")
    @Indexed(unique = true)
    private String shortUrl;
}
