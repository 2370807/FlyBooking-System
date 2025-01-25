package com.cts.flybooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cts.flybooking.security.JwtAuthFilter;
import com.cts.flybooking.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf((csrf)-> csrf.disable())
		.cors(Customizer.withDefaults())
		.authorizeHttpRequests((authorize)->authorize
			.requestMatchers("/Passenger/register/**",
					"/Passenger/login",
					"/Booking/intiatebooking/**",
					"/flight/sources",
					"/flight/destinations",
					"/flight/flightbysourceanddestination/**",
					"/flight/allflight",
					"/flight/flightbysourceanddestinationanddate/**",
					"/Booking/BookingByFlight/**",
					"/Booking/BookingByUser/**",
					"/cancel/**",
					"/Passenger/remove/**").permitAll()
			.anyRequest().authenticated()
		)
//		.formLogin(Customizer.withDefaults())
		.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configures stateless session management
		.formLogin((form)->form.disable())
		.httpBasic(Customizer.withDefaults())
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Adds JWT filter before the UsernamePasswordAuthenticationFilter
		return http.build();
	}
	
	// Configures the AuthenticationManager bean for authentication management
	@Bean
	public 	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
			return configuration.getAuthenticationManager(); // Returns the AuthenticationManager
		}
	

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all endpoints
                        .allowedOrigins("http://localhost:3000") // Allow React frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Allow credentials (e.g., cookies, authorization headers)
            }
        };
    }

}
