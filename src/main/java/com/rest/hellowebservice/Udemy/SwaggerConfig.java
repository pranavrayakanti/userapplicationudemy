package com.rest.hellowebservice.Udemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletRegistration;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	//updeting stamps on swagger on ui or json
	public static final Contact DEFAULT_CONTACT = new Contact("Pranav Roy", "Roy", "roy9k1@gmail.com");
	
	public static final ApiInfo DEFAULT = new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos",
			DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
			new ArrayList<VendorExtension>());
	
	private Set<String> default_Produces_And_Consumes = new HashSet<String>(
			Arrays.asList("application/json", "application/xml"));

	
	//for swagger doc
	@Bean
	public Docket document() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT).produces(default_Produces_And_Consumes)
				.consumes(default_Produces_And_Consumes);

	}
	
	//for h2-console
	 @Bean
	    ServletRegistrationBean h2servletRegistration(){
	        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
	        registrationBean.addUrlMappings("/h2-console/*");
	        return registrationBean;
	    }

}
