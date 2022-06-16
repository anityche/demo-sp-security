package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	final String logPrefix = "...SecurityConfig";

	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info(logPrefix+ " auth configure");

		// 가상의 계정을 만들어 주는 설정
		// 각각의 계정을 생성하고, 권한을 부여
        String password = passwordEncoder().encode("1111");

        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
        auth.inMemoryAuthentication().withUser("manager").password(password).roles("MANAGER");
        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN");
//		auth.authenticationProvider(authenticationProvider());
    }

	@Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Override
    // js, css, image 설정은 보안 설정의 영향 밖에 있도록 만들어주는 설정.
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    // BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체입니다.
    // Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록합니다.
    public PasswordEncoder passwordEncoder() {
    	log.info(logPrefix+ " passwordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	log.info(logPrefix+ " http configure");
    	http
		        .authorizeRequests()
		        .antMatchers("/","/loginUser").permitAll()
		        .antMatchers("/user").hasRole("USER")
		        .antMatchers("/manager").hasRole("MANAGER")
		        .antMatchers("/admin").hasRole("ADMIN")
		        .anyRequest().authenticated()
		    .and()
		        .formLogin();
//		        .loginPage("/login")                    // controller mapping
//		        .loginProcessingUrl("/login_proc")      // th:action="@{/login_proc}"
//		        .defaultSuccessUrl("/")
//		        .permitAll();
    }
}
