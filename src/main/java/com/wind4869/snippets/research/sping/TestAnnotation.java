package com.wind4869.snippets.research.sping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TestAnnotation
 *
 * @author wind4869
 * @since 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
}
