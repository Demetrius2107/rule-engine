package com.hua.ruleengine.config;

import com.hua.ruleengine.groovy.GroovyClassLoader;
import com.hua.ruleengine.groovy.IGroovyClassLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RuleEngineAutoConfig {

    @Bean
    public IGroovyClassLoader iGroovyClassLoader(){
        return new GroovyClassLoader();
    }



}
