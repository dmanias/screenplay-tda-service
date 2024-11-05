package com.cleanarchitecture.domain.valueobject;

import lombok.Value;
import java.util.regex.Pattern;

@Value
public class Email implements ValueObject {
    String value;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public Email(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.value = value;
    }

    private static boolean isValid(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
