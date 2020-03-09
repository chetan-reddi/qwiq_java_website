package com.transport.configuration;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @author krishna Data source configuration
 *
 */
@Configuration
public class DataSourceConfiguration {
	public static final Logger LOG = LoggerFactory.getLogger(DataSourceConfiguration.class);
	@Value("${spring.datasource.url}")
	public String dataSourceUrl;
	@Value("${spring.datasource.username}")
	public String dataSourceUsername;
	@Value("${spring.datasource.password}")
	public String dataSourcePassword;
	@Value("${spring.datasource.driver-class-name}")
	public String dataSourceDriverClassName;
	@Value("${spring.datasource.hikari.poolName}")
	public String dataSourcePoolName;
	@Value("${spring.datasource.hikari.autoCommit}")
	public Boolean dataSourceIsAutoCommit;
	@Value("${spring.datasource.hikari.maximumPoolSize}")
	public int dataSourceMaxPoolSize;
	@Value("${spring.datasource.hikari.minimumIdle}")
	public int dataSourceMinimumIdle;
	@Value("${spring.datasource.hikari.maxLifetime}")
	public Long dataSourceMaxLifetime;

	@Bean
	public DataSource dataSourceContextSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(dataSourceUrl);
		dataSource.setDriverClassName(dataSourceDriverClassName);
		dataSource.setUsername(dataSourceUsername);
		dataSource.setPassword(dataSourcePassword);
		dataSource.setPoolName(dataSourcePoolName);
		dataSource.setAutoCommit(dataSourceIsAutoCommit);
		dataSource.setMaximumPoolSize(dataSourceMaxPoolSize);
		dataSource.setMinimumIdle(dataSourceMinimumIdle);
		dataSource.setMaxLifetime(dataSourceMaxLifetime);
		return dataSource;
	}

}