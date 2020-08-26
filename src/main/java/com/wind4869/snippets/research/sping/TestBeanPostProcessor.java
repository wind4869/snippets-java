package com.wind4869.snippets.research.sping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * TestBeanPostProcessor
 *
 * @author wind4869
 * @since 1.0.0
 */
@Slf4j
@Component
public class TestBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("Bean {} was initialized", beanName);
        return bean;
    }
}
