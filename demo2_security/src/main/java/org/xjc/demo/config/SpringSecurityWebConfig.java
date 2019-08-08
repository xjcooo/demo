package org.xjc.demo.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by xjc on 2019-8-1.
 */
@Configuration
public class SpringSecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and()
                .formLogin().permitAll();
//                .httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.debug(true);// debug日志配置
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin")
//                .password(new BCryptPasswordEncoder().encode("admin"))
//                .roles("ADMIN");
//    }

    /*使用@Autowired方式, 可以适用跨越多个WebSecurityConfigurerAdapter的情况,*/
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return new User("user", new BCryptPasswordEncoder().encode("user"), Lists.<GrantedAuthority>newArrayList());
            }
        }).passwordEncoder(new BCryptPasswordEncoder());
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("user")
//                .password(new BCryptPasswordEncoder().encode("user"))
//                .roles("USER");
    }

}
