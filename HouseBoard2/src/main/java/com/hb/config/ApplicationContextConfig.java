package com.hb.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.hb.model.*;

@Configuration
@ComponentScan("com.hb")
@EnableTransactionManagement
@PropertySources(value = {@PropertySource("classpath:log4j.properties") })
@EnableWebMvc
public class ApplicationContextConfig {
	/**
	 * 
	 */
	/*   @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }*/


	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		/*dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/houseboard");
		dataSource.setUsername("root");
		dataSource.setPassword("root");*/
		dataSource.setDriverClassName(readDBConfigProperty("Driver_Name"));
		dataSource.setUrl(readDBConfigProperty("Connection_Url"));
		dataSource.setUsername(readDBConfigProperty("User_Name"));
		dataSource.setPassword(readDBConfigProperty("Password"));

		/*dataSource.setUrl("rds-mysql-houseboard.ctjyx44ztaom.us-east-1.rds.amazonaws.com:3306/houseboard");
    	dataSource.setUsername("houseboard_root");
    	dataSource.setPassword("houseboard_root");*/

		return dataSource;
	}


	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(AssignedGroupToUsers.class);
		sessionBuilder.addAnnotatedClasses(GroupDetails.class);
		sessionBuilder.addAnnotatedClasses(HouseBoardTask.class);
		sessionBuilder.addAnnotatedClasses(InviteReqSentToFrom.class);
		sessionBuilder.addAnnotatedClasses(SignUp.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}
	/**
	 * 
	 */
	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(getSessionFactory(getDataSource()));
	}
	/**
	 * 
	 */
	private String readDBConfigProperty(String key)
	{
		Properties prop = new Properties();
		InputStream input = null;

		try {

			//input = new FileInputStream("C:\\Spring_Workspace\\HouseBoard\\src\\main\\resources\\DBConfigurations.properties");
			input = new FileInputStream("C:\\HouseBoard_DBConfig\\DBConfigurations.properties");
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("Driver_Name"));
			System.out.println(prop.getProperty("Connection_Url"));
			System.out.println(prop.getProperty("User_Name"));
			System.out.println(prop.getProperty("Password"));

			return prop.getProperty(key);


		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
			
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
