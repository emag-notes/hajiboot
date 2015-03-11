package com.example;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author tanabe
 */
@Configuration
public class AppConfig {

  @Autowired
  DataSourceProperties dataSourceProperties;

  DataSource dataSource;

  @Bean
  DataSource realDataSource() {
    this.dataSource = DataSourceBuilder
      .create(this.dataSourceProperties.getClassLoader())
      .url(this.dataSourceProperties.getUrl())
      .username(this.dataSourceProperties.getUsername())
      .password(this.dataSourceProperties.getPassword())
      .build();
    return this.dataSource;
  }

  @Bean
  DataSource dataSource() {
    return new Log4jdbcProxyDataSource(this.dataSource);
  }
}
