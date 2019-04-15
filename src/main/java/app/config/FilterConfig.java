package app.config;

import app.filter.ThreadContextFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class FilterConfig {
    @Bean
    public FilterRegistrationBean <ThreadContextFilter> loggingFilter (){
        FilterRegistrationBean<ThreadContextFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ThreadContextFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
