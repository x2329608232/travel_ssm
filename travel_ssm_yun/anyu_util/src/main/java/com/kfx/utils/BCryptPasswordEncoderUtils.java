package com.kfx.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String s = BCryptPasswordEncoderUtils.encodePassword("anyu");
        //$2a$10$3hBxnL2366Tq.7j1OwhrxuSsA88F0GFCen3xFO44hlC9/rLZibzuG
        //$2a$10$VR7Iz3oxv5TZ/mkUQJ3zSeVz6mitLdcS6sRaWLRXgPpCV8mxEdZ9.
        System.out.println(s);//123+65476576gfsdfsrjhkt5465 盐 salt
    }
}
