package com.mysql.smart.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PwdHash {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PwdHash() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String hashPassword(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public boolean checkPassword(String rawPassword, String hashedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, hashedPassword);
    }

    public static void main(String[] args) {
        PwdHash service = new PwdHash();
        String hashed = service.hashPassword("myPassword");
        System.out.println(hashed);
        System.out.println(service.checkPassword("myPassword", hashed)); // should return true
    }
}
