package com.Gourav.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.Gourav")
public class MyDispatcherServlet
{
	
	@Bean
	public JavaMailSenderImpl mailSender(){
		JavaMailSenderImpl jms=new JavaMailSenderImpl();
		jms.setHost("smtp.gmail.com");
		jms.setPort(587);
		jms.setUsername("craftygourav.sdbc@gmail.com");
		jms.setPassword("password");
		Properties properties=new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		jms.setJavaMailProperties(properties);
		return jms;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver()
	{
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(999999999);
		
		return resolver;
	}
	
	
	@Bean
	public ComboPooledDataSource myDataSource() throws PropertyVetoException
	{
		ComboPooledDataSource cpdObject = new ComboPooledDataSource();
		
		cpdObject.setDriverClass("com.mysql.jdbc.Driver");
		cpdObject.setJdbcUrl("jdbc:mysql://localhost:3306/transportdeals");
		cpdObject.setUser("root");
		cpdObject.setPassword("password");
		
		return cpdObject;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFacotory() throws PropertyVetoException
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan("com.Gourav");
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.put("hibernate.show_sql","true");
		properties.put("hibernate.hbm2ddl.auto","update");
		
		sessionFactory.setHibernateProperties(properties);
		
		return sessionFactory;
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver()
	{
		InternalResourceViewResolver IRVObject = new InternalResourceViewResolver();
		IRVObject.setSuffix(".jsp");
        IRVObject.setPrefix("/WEB-INF/views/"); 
		
		return IRVObject; 
	} 
}
