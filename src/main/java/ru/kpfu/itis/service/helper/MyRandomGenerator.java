package ru.kpfu.itis.service.helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by vladislav on 07.05.17.
 */
@Component
public class MyRandomGenerator {
    public String generateRandomString(int size){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return RandomStringUtils.random( size, characters );
    }
}
