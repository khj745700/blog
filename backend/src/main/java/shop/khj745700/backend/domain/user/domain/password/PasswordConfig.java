package shop.khj745700.backend.domain.user.domain.password;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InitializingBean passwordConverterInitializer(PasswordEncoder passwordEncoder) {
        return () -> PasswordConverter.setPasswordEncoder(passwordEncoder);
    }

    @Bean
    public InitializingBean passwordMatcherInitializer(PasswordEncoder passwordEncoder) {
        return () -> PasswordMatcher.setPasswordEncoder(passwordEncoder);
    }
}
