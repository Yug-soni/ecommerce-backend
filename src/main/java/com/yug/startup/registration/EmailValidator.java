package com.yug.startup.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String email) {
        // TODO write the reg-exp to validate the email.
        return true;
    }
}
