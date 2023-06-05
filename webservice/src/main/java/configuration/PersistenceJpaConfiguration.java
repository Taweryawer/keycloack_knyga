package configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:db/db.properties")
@EnableTransactionManagement
public class PersistenceJpaConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(env.getRequiredProperty("db.url"));
        dataSource.setUser(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
        dataSource.setDriverClass(env.getRequiredProperty("db.driver"));
        dataSource.setMinPoolSize(Integer.parseInt(env.getRequiredProperty("db.c3p0_min")));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getRequiredProperty("db.c3p0_max")));
        dataSource.setInitialPoolSize(Integer.parseInt(env.getRequiredProperty("db.c3p0_min")));
        dataSource.setAcquireIncrement(Integer.parseInt(env.getRequiredProperty("db.c3p0_acquire_increment")));
        dataSource.setIdleConnectionTestPeriod(Integer.parseInt(env.getRequiredProperty("db.c3p0_idle_test_period")));
        dataSource.setDataSourceName(env.getRequiredProperty("db.dataSourceName"));

        return dataSource;
    }

    @Bean
    @Qualifier("hibernateProperties")
    public Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", env.getRequiredProperty("db.dialect"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("db.hbm2ddl_auto"));
        return hibernateProperties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
        throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("domain");
        em.setJpaProperties(hibernateProperties());

        JpaVendorAdapter hibernateVendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(hibernateVendorAdapter);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager()
        throws PropertyVetoException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public Flyway flyway() throws PropertyVetoException {
        return Flyway.configure().dataSource(dataSource()).load();
    }
}
