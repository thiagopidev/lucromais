package tec.br.lucromais.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import tec.br.lucromais.services.AppUserDetailsService;

/**
 * Classe de configuração de autenticação e autorização de usuários
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Configuration
public class WebSecurityConfiguration {
	
	/**
	 * Injeção de dependência com a classe de serviço de verificação e carregamento de usuário
	**/
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	
	/**
	 * Configurações de autenticação, autorizações, permissões e sessão
	**/
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		requestCache.setMatchingRequestParameterName(null);
		http.requestCache(
			cache -> {
				cache.requestCache(requestCache);
			}
		)
		.authorizeHttpRequests(
			authorize -> {
				authorize.requestMatchers("/login", "/css/**", "/images/**", "/js/**", "/vendors/**").permitAll();
				authorize.anyRequest().authenticated();
			}
		)
		.formLogin(
			form -> {
				form.loginPage("/login");
				form.defaultSuccessUrl("/", true);
				form.usernameParameter("username");
				form.passwordParameter("password");
				form.failureUrl("/login?error=true");
			}
		)
		.logout(
			shutdown -> {
				shutdown.logoutSuccessUrl("/login");
				shutdown.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
			}
		)
		.sessionManagement(
			session -> {
				session.maximumSessions(1);
				session.invalidSessionUrl("/login");
			}
		);
		return http.build();
	}
	
	/**
	 * Autenticação via banco de dados e criptografia de senha
	**/
	@Bean
	public DaoAuthenticationProvider provider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(appUserDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
}