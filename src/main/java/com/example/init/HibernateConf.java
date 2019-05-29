package com.example.init;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableTransactionManagement

public class HibernateConf {
	
	
	
	@Bean
	public DataSource datasource() {
		DataSource dataSource = DataSourceBuilder.create()
				.driverClassName("org.postgresql.Driver")
				.url("jdbc:postgresql://localhost:5432/pensions")
				.username("ashley")
				.password("123").build();
		return dataSource;
	}
	
	

	 @Bean
	 public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

	 HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	 vendorAdapter.setDatabase(Database.POSTGRESQL);
	 vendorAdapter.setGenerateDdl(true);

	 LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	 em.setDataSource(datasource());
	 em.setPackagesToScan("com");
	 em.setJpaVendorAdapter(vendorAdapter);
	 em.setJpaProperties(hibernateProperties());

	 return em;
	 }

	

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL93Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		properties.put("hibernate.jdbc.lob.non_contextual_creation", "true");
		return properties;
	}


	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
	
	 @Bean
	 public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
	 JpaTransactionManager transactionManager = new JpaTransactionManager();
	 transactionManager.setEntityManagerFactory(emf);

	 return transactionManager;
	 }

	 @Bean
	 public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	 return new PersistenceExceptionTranslationPostProcessor();
	 }
	
	
	
	
}