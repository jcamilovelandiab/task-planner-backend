package taskplanner.app.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import taskplanner.app.apirest.config.JwtFilter;

@SpringBootApplication
public class TaskPlannerApp {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter( new JwtFilter() );
		registrationBean.addUrlPatterns( "/api/*" );
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskPlannerApp.class, args);
	}

}
