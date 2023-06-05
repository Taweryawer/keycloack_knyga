package configuration;

import handlers.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration {

    @Primary
    @Bean
    public GlobalExceptionHandler restResponseEntityExceptionHandler (){
        return new GlobalExceptionHandler();
    }

}
