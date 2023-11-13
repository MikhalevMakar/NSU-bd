package ru.nsu.ccfit.mikhalev.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfiguration {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                                                              new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages_ru_Ru.properties");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}