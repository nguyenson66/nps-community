package npscommunity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import npscommunity.services.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
		// Setting Service to find User in the database and Setting PassswordEncoder
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		//Các trang không yêu cầu login
		http.authorizeRequests().antMatchers("/", "/logout", "/question").permitAll();
		
		//Trang chỉ dành cho ADMIN
		http.authorizeRequests().antMatchers().hasRole("ADMIN");
		
		//Trang /ask yêu cầu phải login với vai trò ADMIN hoặc USER
		http.authorizeRequests().antMatchers("/ask").hasAnyRole("ADMIN", "USER");
		
		//Khi người dùng đã login với vai trò XX nhưng truy cập vào trang yêu cầu vai trò YY 
		//-> Hiển thị ngoại lệ
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		//Cấu hình cho form login
			http.authorizeRequests().and().formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/question")
				.failureUrl("/login?error=true")
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
			
		//Cấu hình Remember Me
		http.requestMatchers().and().rememberMe().userDetailsService(userDetailsService).tokenRepository(this.persistentTokenRepository()).tokenValiditySeconds(10*60);
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}
}
