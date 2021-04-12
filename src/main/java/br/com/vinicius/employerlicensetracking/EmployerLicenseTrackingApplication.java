package br.com.vinicius.employerlicensetracking;

import java.util.HashMap;
import java.util.Map;

import javax.faces.webapp.FacesServlet;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.com.vinicius.employerlicensetracking.scope.ViewScope;

@SpringBootApplication
public class EmployerLicenseTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployerLicenseTrackingApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		return new ServletRegistrationBean<FacesServlet>(servlet, "*.jsf");
	}
	
	@Bean
	public static CustomScopeConfigurer customScopeConfigurer() {
		Map<String, Object> scopes = new HashMap<>();
		scopes.put("view", new ViewScope());

		CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
		customScopeConfigurer.setScopes(scopes);

		return customScopeConfigurer;
	}

}
