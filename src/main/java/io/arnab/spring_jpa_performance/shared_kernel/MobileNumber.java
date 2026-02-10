package io.arnab.spring_jpa_performance.shared_kernel;

import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public record MobileNumber(String value) {
    private static final Pattern pattern = Pattern.compile("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6-9]\\d{9}$");

    public static boolean isValid(String value) {
        return pattern.matcher(value).matches();
    }

    public MobileNumber {
        if (value == null || value.isBlank() || !isValid(value)) {
            throw new IllegalArgumentException("invalid mobile number: " + value);
        }
    }
}
