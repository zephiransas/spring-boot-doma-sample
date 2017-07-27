package com.example.doma;


import org.seasar.doma.Domain;

@Domain(valueType = String.class, factoryMethod = "of")
public class Email {

    private final String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        // TODO: validation


        return new Email(value);
    }

    public String getValue() {
        return value;
    }

}
