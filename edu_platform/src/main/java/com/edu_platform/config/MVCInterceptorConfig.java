package com.edu_platform.config;

import com.edu_platform.utils.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class MVCInterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/signin").setViewName("sign-in");
        registry.addViewController("/signup").setViewName("sign-up");
        registry.addViewController("/main").setViewName("agency-chart");
        registry.addViewController("/forgetpassword").setViewName("/forget-password");
    }

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        /*InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/**"); //所有路径都被拦截
        registration.excludePathPatterns(    //添加不拦截路径
                "/login",                    //登录路径
                "/register",
                "/signin",
                "/signup",
                "/static/css/**",
                "/static/js/**",
                "/static/imgs/**",
                "/map/queryName"
        );*/
    }

    /**
     * 设置静态资源
     * 若未设置，配置拦截器后，无法访问到静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}