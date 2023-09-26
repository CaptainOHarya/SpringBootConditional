package ru.netology.springboothomework.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import ru.netology.springboothomework.profile.DevProfile;
import ru.netology.springboothomework.profile.ProductionProfile;
import ru.netology.springboothomework.profile.SystemProfile;
@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "profile.dev", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
