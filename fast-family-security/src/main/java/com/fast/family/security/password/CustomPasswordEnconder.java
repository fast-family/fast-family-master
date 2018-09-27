package com.fast.family.security.password;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/23-17:26
 */
public class CustomPasswordEnconder implements PasswordEncoder{

    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
