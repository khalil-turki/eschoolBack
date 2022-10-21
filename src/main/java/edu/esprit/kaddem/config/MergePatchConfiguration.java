package edu.esprit.kaddem.config;


import edu.esprit.kaddem.converters.MergePatchHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class MergePatchConfiguration extends WebMvcConfigurationSupport {
    @Autowired private MergePatchHttpMessageConverter mergePatchHttpMessageConverter;
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mergePatchHttpMessageConverter);
        addDefaultHttpMessageConverters(converters);
    }
}