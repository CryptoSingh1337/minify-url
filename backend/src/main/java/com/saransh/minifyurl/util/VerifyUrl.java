package com.saransh.minifyurl.util;

import java.util.regex.Pattern;

/**
 * Created by CryptoSingh1337 on 6/17/2021
 */
public class VerifyUrl {

    public static boolean isValid(String longUrl) {
        return Pattern.compile("^htt(p|ps)://www[.].*").matcher(longUrl).matches();
    }
}
