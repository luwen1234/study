package com.luwen.applicationContentTest.config;

import com.test.applicationContentTest.entity.RedisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({RedisConfig.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@Configuration
public class ImportConfig {
}
