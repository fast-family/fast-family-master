package com.fast.family.security.password;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 张顺
 * @version 1.0
 */
public class CustomPasswordEnconder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return PasswordUtils.encryptPassword(Hex.decode(charSequence));
    }

    @Override
    public boolean matches(CharSequence charSequence, String password) {
        return PasswordUtils.matchPassword(charSequence.toString(), password);
    }
}
