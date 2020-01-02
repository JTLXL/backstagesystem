package com.jtl.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author JT.L
 * @date 2020/1/2 18:33:46
 * @description
 */
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "123";
        //每次加密的结果都不一样
        String pwd = encodePassword(password);
        System.out.println(pwd);
    }
}
