package com.xpand.challenge.model.constants;

import java.util.stream.Stream;

public enum Gender {
    M("Male"),
    F("Female");

    public final String label;

    private Gender(String label) {
        this.label = label;
    }

    public static Gender valueOfLabel(String label) {
        for (Gender gender : values()) {
            if (gender.label.equals(label)) {
                return gender;
            }
        }
        return null;
    }

    public static String valueOfName(final Gender gender) {
        return Stream.of(values()).filter(value -> value.equals(gender))
                .findFirst().orElseThrow(IllegalArgumentException::new).label;
    }
}
