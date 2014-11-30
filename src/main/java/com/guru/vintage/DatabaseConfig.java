package com.guru.vintage;
//package com.guru.configuration;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//
//@Configuration
//@EnableTransactionManagement
//@ComponentScan("com.guru")
//public class DatabaseConfig {
//
//	private static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.jdbc.Driver";
//	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "gurukul";
//	private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:mysql://localhost:1000/gurukul";
//	private static final String PROPERTY_NAME_DATABASE_USERNAME = "gurukul";
//
//	@Resource
//	private Environment env;
//
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//		dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
//		dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
//		dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
//		dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
//
//		return dataSource;
//	}
//
//}
