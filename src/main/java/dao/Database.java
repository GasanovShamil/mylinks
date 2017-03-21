package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

@Named
@ApplicationScoped
public class Database implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DataSource datasource;

	public Database() {
		// TODO Auto-generated constructor stub
	}

	public Connection getConnection() {
		try {
			return datasource.getConnection();
		} catch (SQLException e) {
			return null;
		}
	}

	@PostConstruct
	public void init() {
		PoolProperties p = new PoolProperties();
		p.setUrl("jdbc:mysql://localhost:3306/jee");
		p.setDriverClassName("com.mysql.jdbc.Driver");
		p.setUsername("mylinksuser");
		p.setPassword("mylinksuser");
		p.setJmxEnabled(true);
		p.setTestWhileIdle(false);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(100);
		p.setInitialSize(10);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		datasource = new DataSource();
		datasource.setPoolProperties(p);
	}
	
	@PreDestroy
	public void destroy(){
		datasource.close();
	}
}
