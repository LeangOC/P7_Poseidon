package com.nnk.springboot;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class GenBcrypt {
    public static void main(String[] args) {
        //BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("Password123"));
    }
}
