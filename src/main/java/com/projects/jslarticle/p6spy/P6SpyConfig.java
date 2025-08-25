package com.projects.jslarticle.p6spy;

import com.projects.jslarticle.p6spy.P6SpyEventListener;
import com.projects.jslarticle.p6spy.P6SpyFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 탁영복
 * @since 2025-08-25
 * @version 1.0
 * @description p6spy 의 의존성 주입설정을 추가합니다.
 */
@Configuration
public class P6SpyConfig {

    @Bean
    public P6SpyEventListener p6SpyCustomEventListener() {
        return new P6SpyEventListener();
    }

    @Bean
    public P6SpyFormatter p6SpyCustomFormatter() {
        return new P6SpyFormatter();
    }
}