package dev.delivery.delivery.security;

import dev.delivery.delivery.service.DetalheUsuarioServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class JWTConfiguracao extends WebSecurityConfigurerAdapter {

    private final DetalheUsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;

    public JWTConfiguracao(DetalheUsuarioServiceImpl usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuario/salvar").permitAll()
                .antMatchers(HttpMethod.GET, "/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger.html").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/index.html").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/swagger-ui.css").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/swagger-ui-bundle.js").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/swagger-ui-standalone-preset.js").permitAll()
                .antMatchers(HttpMethod.GET, "swagger-ui/favicon-16x16.png").permitAll()
                .antMatchers(HttpMethod.GET, "/api-docs/swagger-config").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/favicon-16x16.png").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/favicon-32x32.png").permitAll()
                .anyRequest()
                .authenticated().and().addFilter(new JWTAutenticarFilter(authenticationManager()))
                .addFilter(new JWTValidarFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
