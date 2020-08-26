package com.wind4869.snippets.research.sping;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TestServiceImpl
 *
 * @author wind4869
 * @since 1.0.0
 */
@Service
public class TestServiceImpl implements TestService {
    @Value("${greeting}")
    private String greeting;

    @Override
    public String welcome(String name) {
        return String.format("%s, %s", greeting, name);
    }
}
